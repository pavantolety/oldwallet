package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponData;

@Repository
public class CSVBulkUploadDAOImpl implements CSVBulkUploadDAO {

	public static final String GET_COUPON_DATA_BY_CODE= "SELECT * FROM COUPONS WHERE COUPON_CODE=?";
	
	public static final String CREATE_COUPON_DATA = "INSERT INTO COUPONS (COUPON_CODE,COUPON_VALUE,COUPON_HIDE_LOCATION,REDEEM_STATUS,VALIDITY_PERIOD,VALID_FROM,VALID_TO,AVAILABLE_REDEMPTIONS,COUNTRY_CODE)VALUES(?,?,?,?,?,?,?,?,?)";
	
	public static final String GET_TRACKING_COUPON_DATA = "SELECT COUNT(COUPON_CODE)AS COUPON_COUNT,COUPON_HIDE_LOCATION AS LOCATION,COUNTRY_CODE AS C_CODE,REDEEM_STATUS,COMPLETED_REDEMPTIONS,REDEEMED_BY FROM COUPONS GROUP BY COUNTRY_CODE ,REDEEM_STATUS";
	private JdbcTemplate jdbcTemplate;

	 @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	 
	@Override
	public boolean  getCouponData(String couponCode) {
		// getting Coupon data by coupon code
		boolean isThere = false;
		List<Map<String,Object>> myList = jdbcTemplate.queryForList(GET_COUPON_DATA_BY_CODE,couponCode);
		 if(myList.size()>0){
			 isThere = true;
		 }
		
		return isThere;
	}

	@Override
	public boolean createCouponData(CouponData couponData) {
		// Create coupon Data
		boolean created =  false; 
				try{									
		   int i =  jdbcTemplate.update(CREATE_COUPON_DATA,couponData.getCouponCode(),couponData.getCouponValue(),couponData.getCouponHideLocation(),couponData.getReedemStatus(),couponData.getValidityPeriod(),couponData.getValidFrom(),couponData.getValidTo(),couponData.getAvailableRedemptions(),couponData.getCountryCode());
		
		   if(i>0){
			   created=true;
		   }
				}catch (DuplicateKeyException de) {
					de.printStackTrace();
	               }catch(Exception e){
	            	   e.printStackTrace();
	               }
		   return created;
	}

	@Override
	public List<CouponData> getCouponTrackingData() {
		// getting Coupon data by coupon code
		List<CouponData> couponDataList =  new ArrayList<CouponData>();
			
				List<Map<String,Object>> myList = jdbcTemplate.queryForList(GET_TRACKING_COUPON_DATA);
				if(myList.size()>0){
					
				for (Map<String, Object> map : myList) {
					couponDataList.add(retrieveCouponTrackData(map));
					}
				return couponDataList;
				} else {
					
					return null;
				}
	}
	private CouponData retrieveCouponTrackData(Map<String, Object> map) {
		
		CouponData coupon = new CouponData();
		
		if(map.get("COUPON_COUNT")!=null){
		coupon.setCouponCount(Long.parseLong(map.get("COUPON_COUNT").toString()));
		}
		if(map.get("LOCATION")!=null){
			coupon.setCouponHideLocation(map.get("LOCATION").toString());
			}
		if(map.get("REDEEM_STATUS")!=null){
			coupon.setReedemStatus(map.get("REDEEM_STATUS").toString());
			}
		if(map.get("REDEEMED_BY")!=null){
			coupon.setReedemedBy(map.get("REDEEMED_BY").toString());
			}
		if(map.get("COMPLETED_REDEMPTIONS")!=null){
			coupon.setCompletedRedemptions(Long.parseLong(map.get("COMPLETED_REDEMPTIONS").toString()));
			}
		if(map.get("C_CODE")!=null){
			coupon.setCountryCode(map.get("C_CODE").toString());
		}
	
		return coupon;
	}

}
