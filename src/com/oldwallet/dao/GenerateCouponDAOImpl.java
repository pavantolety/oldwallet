package com.oldwallet.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.SaveConfiguration;
import com.oldwallet.dao.DBOperationsDAO;

@Repository
public class GenerateCouponDAOImpl implements GenerateCouponDAO{
	
	private static final Logger LOGGER = Logger.getLogger(GenerateCouponDAOImpl.class);
	
	public final static String FILE_NAME="GenerateCoupons";
	
	public static final String GENERATE_COUPON = "INSERT INTO CREATE_COUPONS (COUPON_COUNT,COUPON_LENGTH,TYPE_A,TYPE_A_LENGTH,TYPE_B,TYPE_B_LENGTH,TYPE_C,TYPE_C_LENGTH,REQUEST_DATE)VALUES(?,?,?,?,?,?,?,?,NOW())";
	
	public static final String STORE_COUPONS = "INSERT INTO GENERATED_COUPONS (COUPON_CODE,GENERATE_REQUEST_DATE)VALUES(?,NOW())";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	DBOperationsDAO dbOperationsDAO;
	
	@Override
	public boolean saveConfiguration(SaveConfiguration saveConfiguration) {
		// TODO Auto-generated method stub
		boolean isInserted = false;
		int result = jdbcTemplate.update(GENERATE_COUPON,
				saveConfiguration.getCouponCount(), saveConfiguration.getCouponLength(),
				saveConfiguration.getTypeA(),saveConfiguration.getTypeALength(),
				saveConfiguration.getTypeB(),saveConfiguration.getTypeBLength(),
				saveConfiguration.getTypeC(),saveConfiguration.getTypeCLength());
		if (result > 0) {
			isInserted = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"saveConfiguration()","GENERATE_COUPON","Success");
		}
		else{
			isInserted = false;
			dbOperationsDAO.createDBOperation(FILE_NAME,"saveConfiguration()","GENERATE_COUPON","Failure");
		}
		return isInserted;
	}

	@Override
	public boolean storeCoupons(String couponCode) {
		// TODO Auto-generated method stub
		boolean isInserted = false;
		int result = jdbcTemplate.update(STORE_COUPONS,couponCode);
		if (result > 0) {
			isInserted = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"storeCoupons()","STORE_COUPONS","Success");
		}
		return isInserted;
	}
	
	
}
