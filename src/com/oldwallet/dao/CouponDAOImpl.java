package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oldwallet.config.SystemParams;
import com.oldwallet.model.Coupon;

@Repository
public class CouponDAOImpl implements CouponDAO{
	
	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE COUPON_CODE=? AND REDEEM_STATUS='NEW'";
	
	private JdbcTemplate jdbcTemplate;

	 @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	
	@Override
	@Transactional
	public Coupon getCouponByCode(String couponCode) {
	     List<Coupon> couponList =  new  ArrayList<Coupon>();
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(VALIDATE_COUPON,couponCode);
		if(coupon.size()>0){
		for (Map<String, Object> map : coupon) {
			couponList.add(retrieveCoupon(map));
			}
		return couponList.get(0);
		} else {
			return null;
		}
	}
	
	private Coupon retrieveCoupon(Map<String, Object> map) {
		
		Coupon coupon = new Coupon();
		
		if(map.get("COUPON_ID")!=null){
		coupon.setCouponId(Long.parseLong(map.get("COUPON_ID").toString()));
		}
		if(map.get("EVENT_ID")!=null){
			coupon.setEventId(Long.parseLong(map.get("COUPON_CODE").toString()));
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
}
