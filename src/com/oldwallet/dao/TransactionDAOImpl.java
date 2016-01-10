package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oldwallet.model.MonthlyCouponsCount;
import com.oldwallet.model.MonthlyRedeemCouponsCount;
import com.oldwallet.model.Transaction;
import com.oldwallet.util.DataRetievar;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String INIT_TRANSACTION = "INSERT INTO TRANSACTION(EVENT_ID, COUPON_ID, COUPON_CODE, COUPON_VALUE, USER_EMAIL, USER_MOBILE, COUPON_RETRIEVE_LOCATION, TRANSACTION_CODE, STATUS) VALUES(?,?,?,?,?,?,?,?,'INIT')";
	private static final String UPDATE_TRANSACTION = "UPDATE TRANSACTION SET TRANSACTION_UPDATION=NOW(), STATUS=?,COUPON_RETRIEVE_LOCATION=?,COUPON_RETRIEVE_LOCATION_CODE=? WHERE TRANSACTION_CODE=?";
	private static final String GET_TRANSACTION_BY_CODE = "SELECT TRANSACTION_ID, EVENT_ID, COUPON_ID, COUPON_CODE, COUPON_VALUE, USER_EMAIL, USER_MOBILE, COUPON_RETRIEVE_LOCATION, TRANSACTION_CODE, TRANSACTION_CREATION, TRANSACTION_UPDATION, STATUS FROM TRANSACTION WHERE TRANSACTION_CODE=?";
	private static final String GET_MONTHLY_REDEEMED_COUPONS_COUNT = "SELECT MONTH(TRANSACTION_UPDATION) MONTH, COUNT(*) COUNT FROM TRANSACTION GROUP BY MONTH(TRANSACTION_UPDATION)";
	private static final String GET_MONTHLY_NEW_COUPONS_COUNT = "SELECT MONTH(VALID_FROM) AS MONTH, COUNT(1) AS TOTAL_COUPONS_COUNT FROM COUPONS GROUP BY MONTH(VALID_FROM)";
	private static final String GET_MONTHLY_EXPIRED_COUPONS_COUNT = "SELECT MONTH(VALID_FROM) AS MONTH, COUNT(1) AS EXPIRED_COUPONS_COUNT FROM COUPONS WHERE REDEEM_STATUS LIKE 'EXPIRED' GROUP BY MONTH(VALID_FROM)";
	public static final  String UPDATE_COUPON = "UPDATE COUPONS SET REDEEMED_BY=?,AVAILABLE_REDEMPTIONS=?,REDEEMED_DATE=NOW() WHERE COUPON_CODE=?";
	public static final  String UPDATE_REFERRAL_COUPON = "UPDATE COUPONS SET AVAILABLE_REDEMPTIONS=? WHERE COUPON_CODE=?";
	public static final  String UPDATE_COUPON_DATA = "UPDATE COUPONS SET REDEEM_STATUS=? WHERE COUPON_CODE=?";
	private static final String GET_TRANSACTION_BY_EMAIL = "SELECT TRANSACTION_ID, EVENT_ID, COUPON_ID, COUPON_CODE, COUPON_VALUE, USER_EMAIL, USER_MOBILE, COUPON_RETRIEVE_LOCATION, TRANSACTION_CODE, TRANSACTION_CREATION, TRANSACTION_UPDATION, STATUS FROM TRANSACTION WHERE USER_EMAIL=?";
	private static final String UPDATE_TRANSACTION_BY_EMAIL = "UPDATE TRANSACTION SET COUPON_VALUE=?  WHERE USER_EMAIL=?";
	@Override
	public boolean initTransaction(Transaction transaction) {
		boolean isInserted = false;
		int result = jdbcTemplate.update(INIT_TRANSACTION, transaction.getEventId(),transaction.getCouponId(), transaction.getCouponCode(), transaction.getCouponValue(), transaction.getUserEmail(), transaction.getUserMobile(), transaction.getCouponRetreiveLocation(), transaction.getTransactionCode());
		if(result>0) {
			isInserted = true;
		}
		return isInserted;
	}

	@Override
	@Transactional
	public boolean UpdateTransaction(Transaction transaction) {
		log.debug("Begining of transaction Update :: "+transaction.getUserEmail());
		boolean isUpdated = false;
		int result = jdbcTemplate.update(UPDATE_TRANSACTION, transaction.getStatus(),transaction.getRedeemedLocation(),transaction.getRedeemedLocationCode(),transaction.getTransactionCode());
		if(result>0) {
			isUpdated = true;
			int result1 = jdbcTemplate.update(UPDATE_COUPON,transaction.getUserEmail(),transaction.getAvailableRedemptions()-1, transaction.getCouponCode());
			if(result1>0) {
				log.debug("COUPON UPDATED ::");
			} else {
				log.debug("COUPON UPDATION FAILED ::");
			}
		}
		return isUpdated;
	}
	@Override
	@Transactional
	public boolean UpdateRedeemedTrasaction(Transaction transaction) {
		log.debug("Begining of transaction Update :: "+transaction.getUserEmail());
		boolean isUpdated = false;
		int result = jdbcTemplate.update(UPDATE_TRANSACTION, transaction.getStatus(),transaction.getRedeemedLocation(),transaction.getRedeemedLocationCode(), transaction.getTransactionCode());
		if(result>0) {
			isUpdated = true;
			int result1 = jdbcTemplate.update(UPDATE_REFERRAL_COUPON,transaction.getAvailableRedemptions()-1, transaction.getCouponCode());
			if(result1>0) {
				log.debug("COUPON UPDATED ::");
			} else {
				log.debug("COUPON UPDATION FAILED ::");
			}
		}
		return isUpdated;
	
	}
	@Override
	public boolean updateCoupon(String couponCode) {
		boolean isUpdated = false;
		
		int result1 = jdbcTemplate.update(UPDATE_COUPON_DATA,"REDEEMED",couponCode);
		if(result1>0){
			isUpdated= true;
		}
		
		return isUpdated;
	}
	
	@Override
	public boolean updateCoupon(String couponCode, String couponStatus) {
		boolean isUpdated = false;
		
		int result1 = jdbcTemplate.update(UPDATE_COUPON_DATA,couponStatus,couponCode);
		if(result1>0){
			isUpdated= true;
		}
		
		return isUpdated;
	}
	
	@Override
	public Transaction getTransactionDetailsById(String transCode) {
		log.debug("Begining of getTransaction :: "+transCode);
		List<Transaction> transactions = new ArrayList<Transaction>();
		List<Map<String, Object>> transactionMap = jdbcTemplate.queryForList(GET_TRANSACTION_BY_CODE,transCode);
		if(transactionMap.size()>0){
			log.debug("There is transaction ::: ");
		for (Map<String, Object> map : transactionMap) {
			transactions.add(retrieveTransaction(map));
			}
		return transactions.get(0);
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
		
	}
	@Override
	public Transaction getTransactionDetailsByEmail(String email) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		List<Map<String, Object>> transactionMap = jdbcTemplate.queryForList(GET_TRANSACTION_BY_EMAIL,email);
		if(transactionMap.size()>0){
			log.debug("There is transaction ::: ");
		for (Map<String, Object> map : transactionMap) {
			transactions.add(retrieveTransaction(map));
			}
		return transactions.get(0);
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
		
		
	}

	private Transaction retrieveTransaction(Map<String, Object> map) {
		log.debug("Beginning of Transaction Retrieve");
		Transaction transaction = new Transaction();
		
		transaction.setCouponCode(DataRetievar.getStringValue("COUPON_CODE", map));
		transaction.setCouponId(DataRetievar.getStringValue("COUPON_ID", map));
		transaction.setCouponRedeemTime(DataRetievar.getStringValue("COUPON_REDEEM_TIME", map));
		transaction.setCouponRetreiveLocation(DataRetievar.getStringValue("COUPON_RETRIEVE_LOCATION", map));
		transaction.setCouponValue(DataRetievar.getStringValue("COUPON_VALUE", map));
		transaction.setEventId(DataRetievar.getStringValue("EVENT_ID", map));
		transaction.setId(DataRetievar.getStringValue("TRANSACTION_ID", map));
		transaction.setStatus(DataRetievar.getStringValue("STATUS", map));
		transaction.setTransactionCode(DataRetievar.getStringValue("TRANSACTION_CODE", map));
		transaction.setTransactionUpdate(DataRetievar.getStringValue("TRANSACTION_UPDATION", map));
		transaction.setTransactonCreaton(DataRetievar.getStringValue("TRANSACTION_CREATION", map));
		transaction.setUserEmail(DataRetievar.getStringValue("USER_EMAIL", map));
		transaction.setUserMobile(DataRetievar.getStringValue("USER_MOBILE", map));
		
		log.debug("End of Transaction Retrieve");
		return transaction;
	}

	@Override
	public List<MonthlyRedeemCouponsCount> getRedeemedCouponsCountByMonth() {
		List<MonthlyRedeemCouponsCount> redeemsCount = new ArrayList<MonthlyRedeemCouponsCount>();
		List<Map<String, Object>> redeemedCouponsCount = jdbcTemplate.queryForList(GET_MONTHLY_REDEEMED_COUPONS_COUNT);
		if(redeemedCouponsCount.size()>0){
			log.debug("There is transaction ::: ");
		for (Map<String, Object> map : redeemedCouponsCount) {
			redeemsCount.add(retrieveRedeemedCount(map));
			}
		return redeemsCount;
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
	}

	private MonthlyRedeemCouponsCount retrieveRedeemedCount(Map<String, Object> map) {
		MonthlyRedeemCouponsCount count = new MonthlyRedeemCouponsCount();
		
		count.setCount(DataRetievar.getStringValue("COUNT", map));
		count.setMonth(DataRetievar.getStringValue("MONTH", map));
		
		return count;
	}

	@Override
	public List<MonthlyRedeemCouponsCount> getCouponsCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonthlyCouponsCount> getMonthlyCouponsCount() {
		
		List<MonthlyCouponsCount> totalCount = new ArrayList<MonthlyCouponsCount>();
		List<Map<String, Object>> newCouponsCount = jdbcTemplate.queryForList(GET_MONTHLY_NEW_COUPONS_COUNT);
		if(newCouponsCount.size()>0){
			log.debug("There is transaction ::: ");
		for (Map<String, Object> map : newCouponsCount) {
			totalCount.add(retrieveCouponsCount(map));
			}
		return totalCount;
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
	}
	
	private MonthlyCouponsCount retrieveCouponsCount(Map<String, Object> map) {
		MonthlyCouponsCount count = new MonthlyCouponsCount();
		
		count.setToltalCouponsCount(DataRetievar.getStringValue("TOTAL_COUPONS_COUNT", map));
		count.setMonth(DataRetievar.getStringValue("MONTH", map));
		
		return count;
	}

	@Override
	public List<MonthlyCouponsCount> getExpiredMonthlyCouponsCount() {

		List<MonthlyCouponsCount> totalCount = new ArrayList<MonthlyCouponsCount>();
		List<Map<String, Object>> newCouponsCount = jdbcTemplate.queryForList(GET_MONTHLY_EXPIRED_COUPONS_COUNT);
		if(newCouponsCount.size()>0){
			log.debug("There is transaction ::: ");
		for (Map<String, Object> map : newCouponsCount) {
			totalCount.add(retrieveExspiredCouponsCount(map));
			}
		return totalCount;
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
	}
	private MonthlyCouponsCount retrieveExspiredCouponsCount(Map<String, Object> map) {
		MonthlyCouponsCount count = new MonthlyCouponsCount();
		
		count.setToltalCouponsCount(DataRetievar.getStringValue("EXPIRED_COUPONS_COUNT", map));
		count.setMonth(DataRetievar.getStringValue("MONTH", map));
		
		return count;
	}

	@Override
	public boolean updateTransactionByEmail(Transaction transaction) {
		boolean isUpdated =  false;
		 int rows = jdbcTemplate.update(UPDATE_TRANSACTION_BY_EMAIL,transaction.getCouponValue(),transaction.getUserEmail());
		 if(rows>0){
			 isUpdated = true;
		 }
		return isUpdated;
	}


	


}
