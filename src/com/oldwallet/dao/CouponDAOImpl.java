package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponStatistics;
import com.oldwallet.model.UserToken;
import com.oldwallet.util.DataRetievar;

@Repository
public class CouponDAOImpl implements CouponDAO {

	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE EVENT_ID IN(SELECT EVENT_ID FROM EVENTS WHERE EVENT_STATUS LIKE 'NEW') AND REDEEM_STATUS LIKE 'NEW' AND COUPON_CODE=? AND VALID_TO >= NOW()";

	public static final String UPDATE_COUPON = "UPDATE COUPONS SET REDEEM_STATUS=?,REDEEMED_DATE=NOW() WHERE COUPON_CODE=?";

	public static final String IS_COUPON_EXISTS = "SELECT * FROM COUPONS WHERE COUPON_CODE = ?";

	public static final String GET_COUPON_VALUES = "SELECT * FROM COUPONS";

	public static final String GET_USER_TOKEN_VALUES = "SELECT * FROM USER_TOKENS WHERE TOKEN =?";

	public static final String GET_TOTAL_COUPON_AMOUNT = "SELECT  SUM(X.TOTAL) AS TOTAL_COUPON_AMOUNT FROM ( SELECT SUM(C.COUPON_VALUE)*SUM(C.AVAILABLE_REDEMPTIONS) AS TOTAL  FROM COUPONS C GROUP BY  C.AVAILABLE_REDEMPTIONS,C.COUPON_CODE)  X ";

	public static final String GET_TOTAL_COUPON_COUNT = "SELECT COUNT(COUPON_CODE) AS TOTAL_COUPON_COUNT  FROM COUPONS";

	public static final String GET_TOTAL_REDEEMED_AMOUNT = "SELECT SUM(COUPON_VALUE) AS REDEEMED_COUPON_AMOUNT  FROM TRANSACTION";

	public static final String GET_TOTAL_REDEEMED_COUNT = "SELECT COUNT(COUPON_CODE) AS REDEEMED_COUNT  FROM COUPONS WHERE REDEEM_STATUS='REDEEMED'";

	public static final String GET_COUPON_DATA_BY_REDEEM_STATUS = "SELECT COUNT(COUPON_CODE) AS TOTAL_COUPON_COUNT ,REDEEM_STATUS FROM COUPONS GROUP BY REDEEM_STATUS";

	public static final String UPDATE_COUPON_BY_COUPON_CODE = "UPDATE COUPONS SET COUPON_VALUE=?,REDEEM_STATUS=?,VALID_FROM=?,VALID_TO=?,AVAILABLE_REDEMPTIONS=? WHERE COUPON_CODE=? AND REDEEM_STATUS='NEW' AND VALID_TO>=NOW()";

	private JdbcTemplate jdbcTemplate;
	private static final Logger log = Logger.getLogger(CouponDAOImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean updateCouponData(Coupon coupon) {
		boolean isUpdated = false;

		int result = jdbcTemplate.update(UPDATE_COUPON_BY_COUPON_CODE, coupon.getCouponValue(), coupon.getRedeemStatus(), coupon.getValidFrom(), coupon.getValidTo(), coupon.getAvailableRedemptions(), coupon.getCouponCode());
		if (result > 0) {
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		log.debug("Beginning of getCouponByCode ::: ");
		List<Coupon> couponList = new ArrayList<Coupon>();
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_COUPON, couponCode);
		if (coupon.size() > 0) {
			log.debug("Calid Coupon is available ::: ");
			for (Map<String, Object> map : coupon) {
				couponList.add(retrieveCoupon(map));
			}
			return couponList.get(0);
		} else {
			log.debug("NO valid coupons available ::: ");
			return null;
		}
	}

	private Coupon retrieveCoupon(Map<String, Object> map) {

		log.debug("Beginnig of retrieveCoupon ::");

		Coupon coupon = new Coupon();

		coupon.setCouponId(DataRetievar.getLongValue("COUPON_ID", map));
		coupon.setEventId(DataRetievar.getLongValue("EVENT_ID", map));
		coupon.setCouponCode(DataRetievar.getStringValue("COUPON_CODE", map));
		coupon.setCouponValue(DataRetievar.getStringValue("COUPON_VALUE", map));
		coupon.setRedeemStatus(DataRetievar.getStringValue("REDEEM_STATUS", map));
		coupon.setCouponHideLocation(DataRetievar.getStringValue("COUPON_HIDE_LOCATION", map));
		coupon.setRedeemedBy(DataRetievar.getStringValue("REDEEMED_BY", map));
		coupon.setAvailableRedemptions(DataRetievar.getIntValue("AVAILABLE_REDEMPTIONS", map));
		coupon.setCompletedRedemptions(DataRetievar.getIntValue("COMPLETED_REDEMPTIONS", map));
		coupon.setRedeemedDate(DataRetievar.getDateValueInString("REDEEMED_DATE", map));
		coupon.setLocation(DataRetievar.getStringValue("", map));
		coupon.setValidityPeriod(DataRetievar.getStringValue("", map));
		coupon.setValidFrom(DataRetievar.getDateValueInString("VALID_FROM", map));
		coupon.setValidTo(DataRetievar.getDateValueInString("VALID_TO", map));

		log.debug("End of retrieveCoupon ::");

		return coupon;
	}

	@Override
	public boolean updateCoupon(String couponCode) {
		boolean isUpdated = false;
		int result = jdbcTemplate.update(UPDATE_COUPON, "EXPIRED", couponCode);
		if (result > 0) {
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean isCouponExists(String couponCode) {
		boolean isExists = false;
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(IS_COUPON_EXISTS, couponCode);
		if (coupon.size() > 0) {
			isExists = true;
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
			return couponList;
		} else {

			return new ArrayList<Coupon>();
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

			return userToken.get(0);

		} else {
			return null;
		}
	}

	private UserToken retriveUserToken(Map<String, Object> map) {

		UserToken userToken = new UserToken();

		userToken.setCouponCode(DataRetievar.getStringValue("COUPON_CODE", map));
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
			return csList.get(0);
		}
		return null;
	}

	@Override
	public CouponStatistics getRedeemedCount() {
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_REDEEMED_COUNT);
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
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_COUPON_AMOUNT);
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
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_TOTAL_REDEEMED_AMOUNT);
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

		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_COUPON_DATA_BY_REDEEM_STATUS);
		List<CouponStatistics> csList = new ArrayList<CouponStatistics>();
		if (!mapList.isEmpty()) {
			for (Map<String, Object> map : mapList) {
				csList.add(retriveCouponStatistics(map));
			}
			return csList;
		}
		return null;
	}

}
