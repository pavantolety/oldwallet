package com.oldwallet.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.UserSession;

@Repository
public class CouponBlockerDAOImpl implements CouponBlockerDAO {
	
	private static final String INSERT_COUPON_BLOCKER = "INSERT INTO COUPON_BLOCKER(USER_SESSION, COUPON_CODE) VALUES(?,?)";
	
	private static final String UPDATE_COUPON_BLOCKER = "UPDATE COUPON_BLOCKER SET STATUS = 'REDEEMED' WHERE USER_SESSION = ?";
	
	private static final String UPDATE_COUPON_BLOCKER_JOB = "UPDATE COUPONS SET COUPON_VALUE= ? ,REDEEM_STATUS = 'NEW' WHERE REDEEM_STATUS LIKE 'BLOCKED' AND DATE_BLOCKED < (NOW() - INTERVAL 10 MINUTE)";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean insertCouponBlocker(UserSession session) {
		boolean isInserted = false;
		int i = jdbcTemplate.update(INSERT_COUPON_BLOCKER, session.getId(), session.getCouponCode());
		if(i>0) {
			isInserted = true;
		}
		return isInserted;
	}

	@Override
	public boolean updateCouponBlocker(UserSession userSession) {
		boolean isUpdated = false;
		int i = jdbcTemplate.update(UPDATE_COUPON_BLOCKER, userSession.getId());
		if(i>0) {
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean updateCouponBlockerJob() {
		boolean isUpdated = false;
		int i = jdbcTemplate.update(UPDATE_COUPON_BLOCKER_JOB,0);
		if(i>0) {
			isUpdated = true;
		}
		return isUpdated;
	}	

}