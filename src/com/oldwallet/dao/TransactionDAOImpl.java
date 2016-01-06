package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	private static final String UPDATE_TRANSACTION = "UPDATE TRANSACTION SET TRANSACTION_UPDATION=NOW(), STATUS=? WHERE TRANSACTION_CODE=?";
	private static final String GET_TRANSACTION_BY_CODE = "SELECT TRANSACTION_ID, EVENT_ID, COUPON_ID, COUPON_CODE, COUPON_VALUE, USER_EMAIL, USER_MOBILE, COUPON_RETRIEVE_LOCATION, TRANSACTION_CODE, TRANSACTION_CREATION, TRANSACTION_UPDATION, STATUS FROM TRANSACTION WHERE TRANSACTION_CODE=?";
	private static final String GET_MONTHLY_REDEEMED_COUPONS_COUNT = "SELECT MONTH(TRANSACTION_UPDATION) MONTH, COUNT(*) COUNT FROM TRANSACTION GROUP BY MONTH(TRANSACTION_UPDATION);";

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
	public boolean UpdateTransaction(Transaction transaction) {
		boolean isUpdated = false;
		int result = jdbcTemplate.update(UPDATE_TRANSACTION, transaction.getStatus(), transaction.getTransactionCode());
		if(result>0) {
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public Transaction getTransactionDetailsById(String transCode) {
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

}
