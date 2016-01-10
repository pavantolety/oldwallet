package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.Coupon;

@Repository
public class AdminLoginDAOImpl implements AdminLoginDAO{
	
	public static final String GET_ADMIN_DETAILS = "SELECT ID, EMAIL_ADDRESS, PASSWORD FROM ADMIN_LOGIN WHERE EMAIL_ADDRESS=?";

	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = Logger.getLogger(CouponDAOImpl.class);

	 @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	 
	 @Override
	 public AdminLogin getAdminByEmailAddress(String emailAddress){
		 List<AdminLogin> adminDetailsList =  new  ArrayList<AdminLogin>();
			List<Map<String, Object>> adminList = jdbcTemplate.queryForList(GET_ADMIN_DETAILS,emailAddress);
			if(adminList.size()>0){				
			for (Map<String, Object> map : adminList) {
				adminDetailsList.add(retrieveAdmin(map));
				}
			return adminDetailsList.get(0);
			} else {				
				return null;
			}
		 
	 }

	private AdminLogin retrieveAdmin(Map<String, Object> map) {
		
		AdminLogin adminLogin = new AdminLogin();	

		if(map.get("ID")!=null){
			adminLogin.setId(Long.parseLong(map.get("ID").toString()));
		}
		if(map.get("EMAIL_ADDRESS")!= null){
			adminLogin.setEmailAddress(map.get("EMAIL_ADDRESS").toString());
		}
		if(map.get("PASSWORD")!= null){
			adminLogin.setPassword(map.get("PASSWORD").toString());
		}
		
		return adminLogin;
	}
}
