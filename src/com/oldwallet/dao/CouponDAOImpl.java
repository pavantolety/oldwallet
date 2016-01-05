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

@Repository
public class CouponDAOImpl implements CouponDAO{
	
	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE COUPON_CODE=? AND REDEEM_STATUS='NEW'";
	
	public static final String UPDATE_COUPON = "UPDATE COUPONS SET REDEEM_STATUS=? WHERE COUPON_CODE=?";
	
	private JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(CouponDAOImpl.class);

	 @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	
	@Override
	public Coupon getCouponByCode(String couponCode) {
		log.debug("Beginning of getCouponByCode ::: ");
	     List<Coupon> couponList =  new  ArrayList<Coupon>();
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_COUPON,couponCode);
		if(coupon.size()>0){
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
		
		Coupon coupon = new Coupon();
		
		if(map.get("COUPON_ID")!=null){
		coupon.setCouponId(Long.parseLong(map.get("COUPON_ID").toString()));
		}
		if(map.get("EVENT_ID")!=null){
			coupon.setEventId(Long.parseLong(map.get("EVENT_ID").toString()));
			}
		if(map.get("COUPON_CODE")!=null){
			coupon.setCouponCode(map.get("COUPON_CODE").toString());
		}
		if(map.get("COUPON_VALUE")!=null) {
			coupon.setCouponValue(map.get("COUPON_VALUE").toString());
		}	
		if(map.get("REDEEMED_STATUS")!=null){
			coupon.setRedeemStatus(map.get("REDEEMED_STATUS").toString());
		}
		if(map.get("COUPON_HIDE_LOCATION")!=null){
		coupon.setCouponHideLocation(map.get("COUPON_HIDE_LOCATION").toString());
		}			
					
		System.out.println("corporation::"+coupon);	
		return coupon;
	}

	@Override
	public boolean updateCoupon(String couponCode) {
		boolean isUpdated = false;
		int result = jdbcTemplate.update(UPDATE_COUPON, "EXPIRED", couponCode);
		if(result>0) {
			isUpdated = true;
		}
		return isUpdated;
	}
}
