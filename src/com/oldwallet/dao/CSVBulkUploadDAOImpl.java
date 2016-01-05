package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.CouponData;

@Repository
public class CSVBulkUploadDAOImpl implements CSVBulkUploadDAO {

	public static final String GET_COUPON_DATA_BY_CODE= "SELECT * FROM COUPONS WHERE COUPON_CODE=?";
	
	public static final String CREATE_COUPON_DATA = "INSERT INTO COUPONS (COUPON_CODE,COUPON_VALUE,COUPON_HIDE_LOCATION,REDEEM_STATUS,VALIDITY_PERIOD,VALID_FROM,VALID_TO)VALUES(?,?,?,?,?,?,?)";
	
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
		   int i =  jdbcTemplate.update(CREATE_COUPON_DATA,couponData.getCouponCode(),couponData.getCouponValue(),couponData.getCouponHideLocation(),couponData.getReedemStatus(),couponData.getValidityPeriod(),couponData.getValidFrom(),couponData.getValidTo());
		
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

}
