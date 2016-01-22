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
import com.oldwallet.util.ExceptionObjUtil;

@Repository
public class CouponDAOImpl implements CouponDAO {
	
	private static final Logger LOGGER = Logger.getLogger(CouponDAOImpl.class);

	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE EVENT_ID IN(SELECT EVENT_ID FROM EVENTS WHERE EVENT_STATUS LIKE 'NEW') AND REDEEM_STATUS LIKE 'NEW' AND COUPON_CODE=? AND VALID_TO >= NOW()";

	public static final String UPDATE_COUPON = "UPDATE COUPONS SET REDEEM_STATUS=?,REDEEMED_DATE=NOW() WHERE COUPON_CODE=?";

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
	
	private static final String CREATE_GENERATED_COUPON_DATA = "INSERT INTO COUPONS (COUPON_CODE,REDEEM_STATUS) VALUES (?,?)";
	
	public static final String BLOCK_COUPON = "UPDATE COUPONS SET REDEEM_STATUS='BLOCKED' WHERE COUPON_CODE=?";
    
	public static final String  CREATE_FUND_ALLOCATION = "INSERT INTO FUND_ALLOCATION(CATEGORY_CODE,TOTAL_COUPON_COUNT,COUPON_VALUE,AVAILABLE_COUNT) VALUES(?,?,,?,?)"; 
	
	public static final String GET_FUND_ALLOCATION = "SELECT * FROM FUND_ALLOCATION";
	
	public static final String GET_FUND_ALLOCATION_BY_ID = "SELECT * FROM FUND_ALLOCATION WHERE FUND_ID = ?";
	
	public static final String UPDATE_FUND_ALLOCATION_BY_ID = "UPDATE FUND_ALLOCATION SET AVAILABLE_COUNT = ? WHERE FUND_ID = ?";
	
	private JdbcTemplate jdbcTemplate;

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
		}		
		return isUpdated;
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		LOGGER.debug("Beginning of getCouponByCode ::: ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(
				VALIDATE_COUPON, couponCode);
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
			LOGGER.debug("Calid Coupon is available ::: ");
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));
			}		
			return couponList.get(0);
		}
		return null;
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
		coupon.setCouponHideLocation(DataRetievar.getStringValue(
				"COUPON_HIDE_LOCATION", map));
		coupon.setRedeemedBy(DataRetievar.getStringValue("REDEEMED_BY", map));
		coupon.setAvailableRedemptions(DataRetievar.getIntValue(
				"AVAILABLE_REDEMPTIONS", map));
		coupon.setCompletedRedemptions(DataRetievar.getIntValue(
				"COMPLETED_REDEMPTIONS", map));
		coupon.setLocation(DataRetievar.getStringValue("", map));
		coupon.setValidityPeriod(DataRetievar.getStringValue("", map));
		try {
			if (map.get("REDEEMED_DATE") != null) {
				coupon.setRedeemedDate(format2.format(format1.parse(map.get(
						"REDEEMED_DATE").toString())));
			}
		} catch (ParseException e) {
			LOGGER.log(Priority.ERROR, e);
			ExceptionObjUtil.saveException("REDEEMED_DATE Exception",
					e.getMessage(),FILE_NAME ,RETRIEVE_COUPON );
		}
		if (map.get("VALID_FROM") != null) {
			try {
				coupon.setValidFrom(format2.format(format1.parse(map.get(
						"VALID_FROM").toString())));
			} catch (ParseException e) {
				LOGGER.log(Priority.ERROR, e);
				ExceptionObjUtil.saveException("VALID_FROM Exception",
						e.getMessage(), FILE_NAME ,RETRIEVE_COUPON );
			}
		}
		if (map.get("VALID_TO") != null) {
			try {
				coupon.setValidTo(format2.format(format1.parse(map.get(
						"VALID_TO").toString())));
			} catch (ParseException e) {
				LOGGER.log(Priority.ERROR, e);
				ExceptionObjUtil.saveException("VALID_TO Exception",
						e.getMessage(),FILE_NAME , RETRIEVE_COUPON);
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
		}
		return isUpdated;
	}

	@Override
	public boolean isCouponExists(String couponCode) {
		boolean isExists = false;
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(
				IS_COUPON_EXISTS, couponCode);
		if (!coupon.isEmpty()) {
			isExists = true;
		}
		return isExists;
	}

	@Override
	public List<Coupon> getCouponData() {
		List<Coupon> couponList = new ArrayList<Coupon>();
		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_COUPON_VALUES);
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				couponList.add(retrieveCoupon(map));
			}
			return couponList;
		} else {
			return new ArrayList<Coupon>();
		}
	}

	@Override
	public UserToken getRedeemKey(String redeemKey) {
		List<UserToken> userToken = new ArrayList<UserToken>();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(
				GET_USER_TOKEN_VALUES, redeemKey);
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				userToken.add(retriveUserToken(map));
			}
			return userToken.get(0);
		} else {
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

		cs.setRedeemedCouponCount(DataRetievar.getLongValue("REDEEMED_COUNT",
				map));
		cs.setRedeemStatus(DataRetievar.getStringValue("REDEEM_STATUS", map));
		cs.setTotalCouponAmount(DataRetievar.getDoubleValue(
				"TOTAL_COUPON_AMOUNT", map));
		cs.setTotalCouponsCount(DataRetievar.getLongValue("TOTAL_COUPON_COUNT",
				map));
		cs.setTotalRedeemedAmount(DataRetievar.getDoubleValue(
				"REDEEMED_COUPON_AMOUNT", map));

		return cs;
	}

	@Override
	public CouponStatistics getTotalCouponCount() {
		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_TOTAL_COUPON_COUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			return csList.get(0);
		}
		return null;
	}

	@Override
	public CouponStatistics getRedeemedCount() {
		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_TOTAL_REDEEMED_COUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			return csList.get(0);
		}
		return null;
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
			return csList.get(0);
		}
		return null;
	}

	@Override
	public CouponStatistics getReedmedAmount() {
		List<Map<String, Object>> mapList = jdbcTemplate
				.queryForList(GET_TOTAL_REDEEMED_AMOUNT);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			return csList.get(0);
		}
		return null;
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
		}
		return csList;
	}

	@Override
	public boolean createGeneratedCouponData(Coupon coupon) {
		boolean isCreated =  false;
		int i =  jdbcTemplate.update(CREATE_GENERATED_COUPON_DATA,coupon.getCouponCode(),coupon.getRedeemStatus());
		if(i>0){
			isCreated = true;
		}
		return isCreated;
	}

	@Override
	public boolean blockCouponCode(Coupon coupon) {
		boolean isBlocked = false;
		String enccCouponcode =  EncryptCouponUtil.enccd(coupon.getCouponCode());
		int i = jdbcTemplate.update(BLOCK_COUPON, enccCouponcode);
		if(i>0) {
			isBlocked = true;
		}
		
		return isBlocked;
	}

	@Override
	public boolean createFundAllocation(FundAllocation fundAllocation) {
		boolean isCreated =  false;
		int i = jdbcTemplate.update(CREATE_FUND_ALLOCATION,fundAllocation.getCategoryCode(),fundAllocation.getTotalCouponCount(),fundAllocation.getCouponValue(),fundAllocation.getTotalCouponCount());
		if(i>0){
			isCreated = true;
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
	     List<Map<String,Object>> mapList =  jdbcTemplate.queryForList(GET_FUND_ALLOCATION);
	     if(mapList.size()>0){
	    	 for(Map<String , Object> map :mapList){
	    		 list.add(retriveFundId(map));
	    	 }
	    	 return list;
	     }
		return null;
	}
    
	 public long retriveFundId(Map<String,Object> map){
		 long fundId = DataRetievar.getLongValue("FUND_ID", map);
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
			return fa.get(0);
		}
		return null;
	}
    public FundAllocation retriveFundAllocation(Map<String,Object> map){
    	FundAllocation fa=  new FundAllocation();
    	fa.setFundId(DataRetievar.getLongValue("FUND_ID", map));
    	fa.setCategoryCode(DataRetievar.getStringValue("CATEGORY_CODE", map));
    	fa.setCouponValue(DataRetievar.getStringValue("COUPON_VALUE", map));
    	fa.setTotalCouponCount(DataRetievar.getStringValue("TOTAL_COUPON_COUNT", map));
    	fa.setRedeemedCount(DataRetievar.getStringValue("REDEEMED_COUNT", map));
    	fa.setAvailableCount(DataRetievar.getStringValue("AVAILABLE_COUNT", map));
    	
    	return fa;
    }
	@Override
	public boolean updateFundAllocation(FundAllocation fundAllocation) {
		boolean isUpdated = false;
		int i = jdbcTemplate.update(UPDATE_FUND_ALLOCATION_BY_ID,(NumberUtils.toLong(fundAllocation.getAvailableCount())-1),fundAllocation.getFundId());
		if(i>0){
			isUpdated = true;
		}
		return isUpdated;
	}



}
