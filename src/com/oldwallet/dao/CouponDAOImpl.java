package com.oldwallet.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponStatistics;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.UserToken;
import com.oldwallet.util.DataRetievar;
import com.oldwallet.util.EncryptCouponUtil;
import com.oldwallet.dao.DBOperationsDAO;

@Repository
public class CouponDAOImpl implements CouponDAO {
	
	private static final Logger LOGGER = Logger.getLogger(CouponDAOImpl.class);

	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE EVENT_ID IN(SELECT EVENT_ID FROM EVENTS WHERE EVENT_STATUS LIKE 'NEW') AND REDEEM_STATUS LIKE 'NEW' AND COUPON_CODE=? AND VALID_TO >= NOW()";
	
	public static final String VALIDATE_BLOCKED_COUPON = "SELECT * FROM COUPONS WHERE EVENT_ID IN(SELECT EVENT_ID FROM EVENTS WHERE EVENT_STATUS LIKE 'NEW') AND REDEEM_STATUS LIKE 'BLOCKED' AND COUPON_CODE=? AND VALID_TO >= NOW()";

	public static final String UPDATE_COUPON = "UPDATE COUPONS SET REDEEM_STATUS=?,REDEEMED_DATE=NOW() WHERE COUPON_CODE=?";
	
	public static final String UPDATE_COUPON_VALUE = "UPDATE COUPONS SET COUPON_VALUE=? WHERE COUPON_CODE=?";

	public static final String IS_COUPON_EXISTS = "SELECT * FROM COUPONS WHERE COUPON_CODE = ?";

	public static final String GET_COUPON_VALUES = "SELECT * FROM COUPONS";

	public static final String GET_USER_TOKEN_VALUES = "SELECT * FROM USER_TOKENS WHERE TOKEN =?";

	public static final String GET_TOTAL_COUPON_AMOUNT = "SELECT  SUM(X.TOTAL) AS TOTAL_COUPON_AMOUNT FROM ( SELECT SUM(C.COUPON_VALUE)*SUM(C.AVAILABLE_REDEMPTIONS) AS TOTAL  FROM COUPONS C GROUP BY  C.AVAILABLE_REDEMPTIONS,C.COUPON_CODE)  X ";

	public static final String GET_TOTAL_COUPON_COUNT = "SELECT COUNT(COUPON_CODE) AS TOTAL_COUPON_COUNT  FROM COUPONS";

	public static final String GET_TOTAL_REDEEMED_AMOUNT = "SELECT SUM(COUPON_VALUE) AS REDEEMED_COUPON_AMOUNT  FROM TRANSACTION";

	public static final String GET_TOTAL_REDEEMED_COUNT = "SELECT COUNT(DISTINCT COUPON_CODE) AS REDEEMED_COUNT ,STATUS AS REDEEM_STATUS FROM TRANSACTION WHERE STATUS='COMPLETE'";

	public static final String GET_COUPON_DATA_BY_REDEEM_STATUS = "SELECT COUNT(COUPON_CODE) AS TOTAL_COUPON_COUNT ,REDEEM_STATUS FROM COUPONS GROUP BY REDEEM_STATUS";

	public static final String UPDATE_COUPON_BY_COUPON_CODE = "UPDATE COUPONS SET COUPON_VALUE=?,REDEEM_STATUS=?,VALID_FROM=?,VALID_TO=?,AVAILABLE_REDEMPTIONS=? WHERE COUPON_CODE=? AND REDEEM_STATUS='NEW'";
	
	private static final String FILE_NAME = "CouponDAOImpl.java";
	
	private static final String RETRIEVE_COUPON = "retrieveCoupon";
	
	private static final String GET_FUND_VALUES= "SELECT COUNT(C.COUPON_CODE) AS TOTAL_COUPON_COUNT ,(SELECT COUNT(C.COUPON_CODE)-SUM(F.REDEEMED_COUNT) FROM FUND_ALLOCATION F)AS REDEEMED_COUNT  FROM COUPONS C ;";
	
	private static final String CREATE_GENERATED_COUPON_DATA = "INSERT INTO COUPONS (COUPON_CODE,REDEEM_STATUS,VALID_FROM,VALID_TO) VALUES (?,?,?,?)";
	
	public static final String BLOCK_COUPON = "UPDATE COUPONS SET REDEEM_STATUS='BLOCKED' ,LATITUDE=?,LONGITUDE=?, DATE_BLOCKED = NOW() WHERE COUPON_CODE=?";
    
	public static final String  CREATE_FUND_ALLOCATION = "INSERT INTO FUND_ALLOCATION(CATEGORY_CODE,TOTAL_COUPON_COUNT,COUPON_VALUE,REDEEMED_COUNT,AVAILABLE_COUNT) VALUES(?,?,?,?,?)"; 
	
	public static final String UPDATE_FUND_ALLOCATION = "UPDATE FUND_ALLOCATION SET TOTAL_COUPON_COUNT=?,COUPON_VALUE=? ,REDEEMED_COUNT=?,AVAILABLE_COUNT=? WHERE CATEGORY_CODE=?";
	
	public static final String GET_FUND_ALLOCATION_BY_CODE = "SELECT * FROM FUND_ALLOCATION WHERE CATEGORY_CODE=?";
	
	public static final String GET_FUND_ALLOCATION = "SELECT * FROM FUND_ALLOCATION";
	
	public static final String GET_FUND_ALLOCATION_BY_ID = "SELECT * FROM FUND_ALLOCATION WHERE FUND_ID = ?";
	
	public static final String UPDATE_FUND_ALLOCATION_BY_ID = "UPDATE FUND_ALLOCATION SET AVAILABLE_COUNT = ? WHERE CATEGORY_CODE = ?";
	
	public static final String GET_REMAINING_TOTAL_VALUE = "SELECT SUM((F.TOTAL_COUPON_COUNT)*(F.COUPON_VALUE)) AS TOTAL_FUND, (SELECT (SUM((F.TOTAL_COUPON_COUNT)*(F.COUPON_VALUE))-SUM(C.COUPON_VALUE)) FROM COUPONS C WHERE REDEEM_STATUS='REDEEMED') AS REMAINING  FROM FUND_ALLOCATION F";
	
	public static final String GET_LAT_LONG_BY_STATUS="SELECT LATITUDE,LONGITUDE FROM COUPONS WHERE REDEEM_STATUS=?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ExceptionObjDAO exceptionDAO;
	
	@Autowired
	DBOperationsDAO dbOperationsDAO;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean updateCouponData(Coupon coupon) {
		boolean isUpdated = false;
		String encypCode = EncryptCouponUtil.enccd(coupon.getCouponCode());
		int result = jdbcTemplate.update(UPDATE_COUPON_BY_COUPON_CODE,coupon.getCouponValue(), coupon.getRedeemStatus(),coupon.getValidFrom(), coupon.getValidTo(),coupon.getAvailableRedemptions(),encypCode);
		if (result > 0) {
			isUpdated = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"updateCouponData()","UPDATE_COUPON_BY_COUPON_CODE","Success");
		}	
		else{
			isUpdated = false;
			dbOperationsDAO.createDBOperation(FILE_NAME,"updateCouponData()","UPDATE_COUPON_BY_COUPON_CODE","Failure");
		}
		return isUpdated;
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		LOGGER.debug("Beginning of getCouponByCode ::: ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_COUPON, couponCode);
		if (!coupon.isEmpty()) {
			LOGGER.debug("Calid Coupon is available ::: ");
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));
			}
			if(couponList.size()>0){
				
				boolean matched =  BCrypt.checkpw(couponCode, couponList.get(0).getCouponCode());
				if(matched){
					return couponList.get(0);
				}else{
					LOGGER.debug("NO valid coupons available ::: ");
					return null;
				}
			}			
		}
		return null;
	}

	@Override
	public Coupon getEncCouponByCode(String couponCode) {
		
		List<Coupon> couponList = new ArrayList<Coupon>();
		String encCode =  EncryptCouponUtil.enccd(couponCode);
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_COUPON, encCode);
		if (!coupon.isEmpty()) {
			LOGGER.debug("Valid Coupon is available getEncCouponByCode ::: ");
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));
			}	
			dbOperationsDAO.createDBOperation(FILE_NAME,"getEncCouponByCode()","VALIDATE_COUPON","Success");
			return couponList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getEncCouponByCode()","VALIDATE_COUPON","Failure");
			return null;
			}
	}
	@Override
	public Coupon getEncBlockedCouponByCode(String couponCode) {
		
		List<Coupon> couponList = new ArrayList<Coupon>();
		String encCode =  EncryptCouponUtil.enccd(couponCode);
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_BLOCKED_COUPON, encCode);
		if (!coupon.isEmpty()) {
			LOGGER.debug("Valid Coupon is available getEncCouponByCode ::: ");
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));				
			}	
			dbOperationsDAO.createDBOperation(FILE_NAME,"getEncBlockedCouponByCode()","VALIDATE_BLOCKED_COUPON","Success");
			return couponList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getEncBlockedCouponByCode()","VALIDATE_BLOCKED_COUPON","Failure");
			return null;
		}
	}
	
	@Override
	public List<Coupon> getCouponDataByRedeemed() {
		
		List<Coupon> couponList = new ArrayList<Coupon>();
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(GET_LAT_LONG_BY_STATUS,"REDEEMED");
		if (!coupon.isEmpty()) {
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));				
			}	
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponDataByRedeemed()","GET_LAT_LONG_BY_STATUS","Success");
			return couponList;
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponDataByRedeemed()","GET_LAT_LONG_BY_STATUS","Failure");			
		}
		return new ArrayList<Coupon>();
	}


	@SuppressWarnings("deprecation")
	private Coupon retrieveCoupon(Map<String, Object> map) {

		LOGGER.debug("Beginnig of retrieveCoupon ::");

		Coupon coupon = new Coupon();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		coupon.setCouponId(DataRetievar.getLongValue("COUPON_ID", map));
		coupon.setEventId(DataRetievar.getLongValue("EVENT_ID", map));
		
		coupon.setCouponCode(EncryptCouponUtil.deccd(DataRetievar.getStringValue("COUPON_CODE", map)));
		coupon.setCouponValue(DataRetievar.getStringValue("COUPON_VALUE", map));
		coupon.setRedeemStatus(DataRetievar.getStringValue("REDEEM_STATUS", map));
		coupon.setCouponHideLocation(DataRetievar.getStringValue("COUPON_HIDE_LOCATION", map));
		coupon.setRedeemedBy(DataRetievar.getStringValue("REDEEMED_BY", map));
		coupon.setAvailableRedemptions(DataRetievar.getIntValue("AVAILABLE_REDEMPTIONS", map));
		coupon.setCompletedRedemptions(DataRetievar.getIntValue("COMPLETED_REDEMPTIONS", map));
		coupon.setLocation(DataRetievar.getStringValue("", map));
		coupon.setValidityPeriod(DataRetievar.getStringValue("", map));
		coupon.setLatitude(DataRetievar.getStringValue("LATITUDE", map));
		coupon.setLongitude(DataRetievar.getStringValue("LONGITUDE", map));
		try {
			if (map.get("REDEEMED_DATE") != null) {
				coupon.setRedeemedDate(format2.format(format1.parse(map.get("REDEEMED_DATE").toString())));
			}
		} catch (ParseException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("REDEEMED_DATE Exception",e.getMessage(),FILE_NAME ,RETRIEVE_COUPON );
		}
		if (map.get("VALID_FROM") != null) {
			try {
				coupon.setValidFrom(format2.format(format1.parse(map.get(
						"VALID_FROM").toString())));
			} catch (ParseException e) {
				LOGGER.log(Priority.ERROR, e);
				exceptionDAO.saveException("VALID_FROM Exception",e.getMessage(), FILE_NAME ,RETRIEVE_COUPON );
			}
		}
		if (map.get("VALID_TO") != null) {
			try {
				coupon.setValidTo(format2.format(format1.parse(map.get(
						"VALID_TO").toString())));
			} catch (ParseException e) {
				LOGGER.log(Priority.ERROR, e);
				exceptionDAO.saveException("VALID_TO Exception",e.getMessage(),FILE_NAME , RETRIEVE_COUPON);
			}
		}

		LOGGER.debug("End of retrieveCoupon ::");

		return coupon;
	}

	@Override
	public boolean updateCoupon(String couponCode) {
		boolean isUpdated = false;
		String enccCouponCode =  EncryptCouponUtil.enccd(couponCode);
		int result = jdbcTemplate.update(UPDATE_COUPON, "REDEEMED", enccCouponCode);
		if (result > 0) {
			isUpdated = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"updateCoupon()","UPDATE_COUPON","Success");			
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"updateCoupon()","UPDATE_COUPON","Failure");
			isUpdated = false;
		}
		return isUpdated;
	}

	@Override
	public boolean isCouponExists(String couponCode) {
		boolean isExists = false;
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(IS_COUPON_EXISTS, couponCode);
		if (!coupon.isEmpty()) {
			isExists = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"isCouponExists()","IS_COUPON_EXISTS","Success");
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"isCouponExists()","IS_COUPON_EXISTS","Failure");
			isExists = false;
		}
		return isExists;
	}

	@Override
	public List<Coupon> getCouponData() {
		List<Coupon> couponList = new ArrayList<Coupon>();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_COUPON_VALUES);
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				couponList.add(retrieveCoupon(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponData()","GET_COUPON_VALUES","Success");
			return couponList;
		} else {
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponData()","GET_COUPON_VALUES","Failure");			
		}
		return new ArrayList<Coupon>();
	}
	@Override
	public FundAllocation getFundData() {
		List<FundAllocation> fundList = new ArrayList<FundAllocation>();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_FUND_VALUES);
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				fundList.add(retriveFundAllocation(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundData()","GET_FUND_VALUES","Success");
			return fundList.get(0);
		} else {
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundData()","GET_FUND_VALUES","Failure");
			return null;
		}
	}
	@Override
	public UserToken getRedeemKey(String redeemKey) {
		List<UserToken> userToken = new ArrayList<UserToken>();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_USER_TOKEN_VALUES, redeemKey);
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				userToken.add(retriveUserToken(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getRedeemKey()","GET_USER_TOKEN_VALUES","Success");
			return userToken.get(0);
		} else {
			dbOperationsDAO.createDBOperation(FILE_NAME,"getRedeemKey()","GET_USER_TOKEN_VALUES","Failure");
			return null;
		}
	}

	private UserToken retriveUserToken(Map<String, Object> map) {

		UserToken userToken = new UserToken();

		userToken
				.setCouponCode(DataRetievar.getStringValue("COUPON_CODE", map));
		userToken.setRequestId(DataRetievar.getLongValue("REQUEST_ID", map));
		userToken.setToken(DataRetievar.getStringValue("TOKEN", map));
		userToken.setUserEmail(DataRetievar.getStringValue("USER_EMAIL", map));

		return userToken;
	}

	public CouponStatistics retriveCouponStatistics(Map<String, Object> map) {
		CouponStatistics cs = new CouponStatistics();

		cs.setRedeemedCouponCount(DataRetievar.getLongValue("REDEEMED_COUNT",map));
		cs.setRedeemStatus(DataRetievar.getStringValue("REDEEM_STATUS", map));
		cs.setTotalCouponAmount(DataRetievar.getDoubleValue("TOTAL_COUPON_AMOUNT", map));
		cs.setTotalCouponsCount(DataRetievar.getLongValue("TOTAL_COUPON_COUNT",map));
		cs.setTotalRedeemedAmount(DataRetievar.getDoubleValue("REDEEMED_COUPON_AMOUNT", map));

		return cs;
	}

	@Override
	public CouponStatistics getTotalCouponCount() {
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_COUPON_COUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getTotalCouponCount()","GET_TOTAL_COUPON_COUNT","Success");
			return csList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getTotalCouponCount()","GET_TOTAL_COUPON_COUNT","Failure");
			return null;
		}
		
	}

	@Override
	public CouponStatistics getRedeemedCount() {
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_REDEEMED_COUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getRedeemedCount()","GET_TOTAL_REDEEMED_COUNT","Success");
			return csList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getRedeemedCount()","GET_TOTAL_REDEEMED_COUNT","Failure");
			return null;
		}
		
	}

	@Override
	public CouponStatistics getTotalCouponAmount() {
		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_TOTAL_COUPON_AMOUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getTotalCouponAmount()","GET_TOTAL_COUPON_AMOUNT","Success");
			return csList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getTotalCouponAmount()","GET_TOTAL_COUPON_AMOUNT","Failure");
			return null;
		}
		
	}

	@Override
	public CouponStatistics getReedmedAmount() {
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_REDEEMED_AMOUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getReedmedAmount()","GET_TOTAL_REDEEMED_AMOUNT","Success");
			return csList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getReedmedAmount()","GET_TOTAL_COUPON_AMOUNT","Failure");
			return null;
		}
		
	}

	@Override
	public List<CouponStatistics> getCouponDataByReedeemStatus() {

		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_COUPON_DATA_BY_REDEEM_STATUS);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponDataByReedeemStatus()","GET_COUPON_DATA_BY_REDEEM_STATUS","Success");
		}
		return csList;
	}

	@Override
	public boolean createGeneratedCouponData(Coupon coupon) {
		boolean isCreated =  false;
		int i =  jdbcTemplate.update(CREATE_GENERATED_COUPON_DATA,coupon.getCouponCode(),coupon.getRedeemStatus(),coupon.getValidFrom(),coupon.getValidTo());
		if(i>0){
			isCreated = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"createGeneratedCouponData()","CREATE_GENERATED_COUPON_DATA","Success");
		}
		return isCreated;
	}

	@Override
	public boolean blockCouponCode(Coupon coupon) {
		boolean isBlocked = false;
		String enccCouponcode =  EncryptCouponUtil.enccd(coupon.getCouponCode());
		int i = jdbcTemplate.update(BLOCK_COUPON, coupon.getLatitude(),coupon.getLongitude(),enccCouponcode);
		if(i>0) {
			isBlocked = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"blockCouponCode()","BLOCK_COUPON","Success");
		}
		
		return isBlocked;
	}

	@Override
	public boolean createFundAllocation(FundAllocation fundAllocation) {
		boolean isCreated =  false;
		int i = jdbcTemplate.update(CREATE_FUND_ALLOCATION,fundAllocation.getCategoryCode(),fundAllocation.getTotalCouponCount(),fundAllocation.getCouponValue(),fundAllocation.getTotalCouponCount(),fundAllocation.getTotalCouponCount());
		if(i>0){
			isCreated = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"createFundAllocation()","CREATE_FUND_ALLOCATION","Success");
		}
		return isCreated;
	}
	@Override
	public boolean updateFundAllocationData(FundAllocation fundAllocation) {
		boolean isCreated =  false;
		int i = jdbcTemplate.update(UPDATE_FUND_ALLOCATION,fundAllocation.getTotalCouponCount(),fundAllocation.getCouponValue(),fundAllocation.getRedeemedCount(),fundAllocation.getAvailableCount(),fundAllocation.getCategoryCode());
		if(i>0){
			isCreated = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"updateFundAllocationData()","UPDATE_FUND_ALLOCATION","Success");
		}
		return isCreated;
	}
	@Override
	public FundAllocation assignValueToCoupon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getAllCategories() {
	     List<Long> list = new ArrayList<Long>();
	     List<Map<String,Object>> mapList =  jdbcTemplate.queryForList("SELECT  * FROM FUND_ALLOCATION WHERE AVAILABLE_COUNT>0");
	     System.out.println("map list size>>>>>>>>>>>>>>>"+mapList.size());
	     if(mapList.size()>0){
	    	 for(Map<String , Object> map :mapList){
	    		 list.add(retriveFundId(map));	    		 
	    	 }
	    	 dbOperationsDAO.createDBOperation(FILE_NAME,"getAllCategories()","GET FUND ALLOCATION","Success");
	    	 return list;
	     }
		return null;
	}
    
	 public long retriveFundId(Map<String,Object> map){
		
		 long fundId = DataRetievar.getLongValue("FUND_ID", map);
		 System.out.println("fundIDDD>>>>>>>>>>"+fundId);
		 return fundId;
	 }
	@Override
	public FundAllocation getFundByCateId(long id) {
		List<FundAllocation>  fa = new ArrayList<FundAllocation>();
		List<Map<String,Object>> mapList  = jdbcTemplate.queryForList(GET_FUND_ALLOCATION_BY_ID,id);
		if(mapList.size()>0){
			for(Map<String,Object> map :mapList){
				fa.add(retriveFundAllocation(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundByCateId()","GET_FUND_ALLOCATION_BY_ID","Success");
			return fa.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundByCateId()","GET_FUND_ALLOCATION_BY_ID","Failure");
			return null;
		}
		
	}
    public FundAllocation retriveFundAllocation(Map<String,Object> map){
    	FundAllocation fa=  new FundAllocation();
    	fa.setFundId(DataRetievar.getLongValue("FUND_ID", map));
    	fa.setCategoryCode(DataRetievar.getStringValue("CATEGORY_CODE", map));
    	fa.setCouponValue(DataRetievar.getStringValue("COUPON_VALUE", map));
    	fa.setTotalCouponCount(DataRetievar.getStringValue("TOTAL_COUPON_COUNT", map));
    	fa.setRedeemedCount(DataRetievar.getStringValue("REDEEMED_COUNT", map));
    	fa.setAvailableCount(DataRetievar.getStringValue("AVAILABLE_COUNT", map));
    	fa.setTotalCouponValue(DataRetievar.getDoubleValue("REMAINING", map));
    	fa.setTotalFund(DataRetievar.getDoubleValue("TOTAL_FUND", map));
    	
    	return fa;
    }
	@Override
	public boolean updateFundAllocation(FundAllocation fundAllocation) {
		boolean isUpdated = false;
		int i;
		if(NumberUtils.toLong(fundAllocation.getAvailableCount())>0){
		        i = jdbcTemplate.update(UPDATE_FUND_ALLOCATION_BY_ID,(NumberUtils.toLong(fundAllocation.getAvailableCount())-1),fundAllocation.getCategoryCode());
		}else{
		        i = jdbcTemplate.update(UPDATE_FUND_ALLOCATION_BY_ID,(NumberUtils.toLong(fundAllocation.getAvailableCount())),fundAllocation.getCategoryCode());
		}
		if(i>0){
			String encCode =  EncryptCouponUtil.enccd(fundAllocation.getCouponCode());
			jdbcTemplate.update(UPDATE_COUPON_VALUE,fundAllocation.getCouponValue(),encCode);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public FundAllocation getFundAllocationData() {
		List<FundAllocation> faList =  new ArrayList<FundAllocation>();
		List<Map<String, Object>> mapList =  jdbcTemplate.queryForList(GET_REMAINING_TOTAL_VALUE);
		if(mapList.size()>0){
			for(Map<String,Object> map:mapList){
				faList.add(retriveFundAllocation(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundAllocationData()","GET_REMAINING_TOTAL_VALUE","Success");
			return faList.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundAllocationData()","GET_REMAINING_TOTAL_VALUE","Failure");
			return null;
		}
		
	}

	@Override
	public FundAllocation getFundAllocationDataByCode(String categoryCode) {
		List<FundAllocation> fd =  new ArrayList<FundAllocation>();
		List<Map<String,Object>> mapList = jdbcTemplate.queryForList(GET_FUND_ALLOCATION_BY_CODE,categoryCode);
		if(mapList.size()>0){
			for(Map<String,Object> map: mapList){
				fd.add(retriveFundAllocation(map));				
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundAllocationDataByCode()","GET_FUND_ALLOCATION_BY_CODE","Success");
			return fd.get(0);
		}
		else{
			dbOperationsDAO.createDBOperation(FILE_NAME,"getFundAllocationDataByCode()","GET_FUND_ALLOCATION_BY_CODE","Failure");
			return null;
		}
		
	}



	



}
