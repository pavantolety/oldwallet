package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.util.DataRetievar;
import com.oldwallet.util.ExceptionObjUtil;
import com.oldwallet.model.SaveConfiguration;;

@Repository
public class GenerateCouponDAOImpl implements GenerateCouponDAO{
	
	private static final Logger LOGGER = Logger.getLogger(GenerateCouponDAOImpl.class);
	
	public static final String GENERATE_COUPON = "INSERT INTO CREATE_COUPONS (COUPON_COUNT,COUPON_LENGTH,TYPE_A,TYPE_A_LENGTH,TYPE_B,TYPE_B_LENGTH,TYPE_C,TYPE_C_LENGTH,REQUEST_DATE)VALUES(?,?,?,?,?,?,?,?,NOW())";
	
	public static final String GET_COUPON_BY_REQUEST_ID = "SELECT COUPON_COUNT,COUPON_LENGTH,TYPE_A,TYPE_A_LENGTH,TYPE_B,TYPE_B_LENGTH,TYPE_C,TYPE_C_LENGTH FROM CREATE_COUPONS WHERE REQUEST_ID=?";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
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
		}
		return isInserted;
	}

	@Override
	public SaveConfiguration getDataById(long requestId) {
		// TODO Auto-generated method stub
		LOGGER.debug("Begining of getTransaction :: " + requestId);
		List<SaveConfiguration> coupons = new ArrayList<SaveConfiguration>();
		List<Map<String, Object>> generateCoupons = jdbcTemplate.queryForList(
				GET_COUPON_BY_REQUEST_ID, requestId);
		if (!generateCoupons.isEmpty()) {
			for (Map<String, Object> map : generateCoupons) {
				coupons.add(retrieveCoupons(map));
			}
			return coupons.get(0);
		} else {
			LOGGER.debug("NO valid data available ::: ");
			return null;
		}
	}
	
	private SaveConfiguration retrieveCoupons(Map<String, Object> map) {
		LOGGER.debug("Beginning of Generate Coupons Retrieve");
		SaveConfiguration generateCoupon = new SaveConfiguration();

		generateCoupon.setCouponCount(DataRetievar.getIntValue("COUPON_COUNT",map));
		generateCoupon.setCouponLength(DataRetievar.getIntValue("COUPON_LENGTH", map));
		generateCoupon.setTypeA(DataRetievar.getStringValue("TYPE_A", map));
		generateCoupon.setTypeALength(DataRetievar.getIntValue("TYPE_A_LENGTH", map));
		generateCoupon.setTypeB(DataRetievar.getStringValue("TYPE_B", map));
		generateCoupon.setTypeBLength(DataRetievar.getIntValue("TYPE_B_LENGTH",	map));
		generateCoupon.setTypeC(DataRetievar.getStringValue("TYPE_C", map));
		generateCoupon.setTypeCLength(DataRetievar.getIntValue("TYPE_C_LENGTH", map));

		LOGGER.debug("End of Generate Coupons Retrieve");
		return generateCoupon;
	}
	
	
}
