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
	
	public static final String VALIDATE_COUPON = "SELECT * FROM COUPONS WHERE EVENT_ID IN(SELECT EVENT_ID FROM EVENTS WHERE EVENT_STATUS LIKE 'NEW') AND REDEEM_STATUS LIKE 'NEW' AND COUPON_CODE=?";
	
	public static final String UPDATE_COUPON = "UPDATE COUPONS SET REDEEM_STATUS=?,REDEEMED_DATE=NOW() WHERE COUPON_CODE=?";
	
	public static final String IS_COUPON_EXISTS = "SELECT * FROM COUPONS WHERE COUPON_CODE = ?";
	
	public static final String GET_COUPON_VALUES = "SELECT * FROM COUPONS";
	
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
		if(map.get("REDEEM_STATUS")!=null){
			coupon.setRedeemStatus(map.get("REDEEM_STATUS").toString());
		}
		if(map.get("COUPON_HIDE_LOCATION")!=null){
		coupon.setCouponHideLocation(map.get("COUPON_HIDE_LOCATION").toString());
		}
		if(map.get("REDEEMED_BY")!=null){
			coupon.setRedeemedBy(map.get("REDEEMED_BY").toString());
			}
		if(map.get("AVAILABLE_REDEMPTIONS")!=null){
			coupon.setAvailableRedemptions(Integer.parseInt(map.get("AVAILABLE_REDEMPTIONS").toString()));
			}
		if(map.get("COMPLETED_REDEMPTIONS")!=null){
			coupon.setCompletedRedemptions(Integer.parseInt(map.get("COMPLETED_REDEMPTIONS").toString()));
			}
		if(map.get("REDEEMED_DATE")!=null){
		   coupon.setRedeemedDate(map.get("REDEEMED_DATE").toString());
		  }
		  if(map.get("LOCATION")!=null){
		   coupon.setLocation(map.get("LOCATION").toString());
		  }
		  if(map.get("VALIDITY_PERIOD")!=null){
		   coupon.setValidityPeriod(map.get("VALIDITY_PERIOD").toString());
		  }
		  if(map.get("VALID_FROM")!=null){
		   coupon.setValidFrom(map.get("VALID_FROM").toString());
		  }
		  if(map.get("VALID_TO")!=null){
		   coupon.setValidTo(map.get("VALID_TO").toString());
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

	@Override
	public boolean isCouponExists(String couponCode) {
		boolean isExists = false;
		List<Map<String, Object>> coupon = jdbcTemplate.queryForList(IS_COUPON_EXISTS,couponCode);
		if(coupon.size()>0) {
			isExists = true;
		}
		return isExists;
	}
	
	@Override
	 public List<Coupon> getCouponData() {
	  List<Coupon> couponList = new ArrayList<Coupon>();
	  List<Map<String, Object>> mapList = jdbcTemplate.queryForList(GET_COUPON_VALUES);
	  if(mapList.size()>0){
	   for(Map<String,Object> map : mapList){
	    couponList.add(retrieveCoupon(map));
	   }
	   return couponList;
	  }else{
	  
	  return null;
	  }
	 }
}
