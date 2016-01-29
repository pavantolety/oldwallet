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

import com.oldwallet.model.CouponData;
import com.oldwallet.util.DataRetievar;
import com.oldwallet.dao.DBOperationsDAO;

@Repository
public class CSVBulkUploadDAOImpl implements CSVBulkUploadDAO {

	private static final Logger LOGGER = Logger.getLogger(CSVBulkUploadDAOImpl.class);
	
	public final static String FILE_NAME="CSV Bulk Upload";

	public static final String GET_COUPON_DATA_BY_CODE = "SELECT * FROM COUPONS WHERE COUPON_CODE=?";

	public static final String CREATE_COUPON_DATA = "INSERT INTO COUPONS (COUPON_CODE,COUPON_VALUE,COUPON_HIDE_LOCATION,REDEEM_STATUS,VALIDITY_PERIOD,VALID_FROM,VALID_TO,AVAILABLE_REDEMPTIONS,COUNTRY_CODE)VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String GET_TRACKING_COUPON_DATA = "SELECT COUNT(COUPON_CODE)AS COUPON_COUNT,COUPON_HIDE_LOCATION AS LOCATION,COUNTRY_CODE AS C_CODE,REDEEM_STATUS,COMPLETED_REDEMPTIONS,REDEEMED_BY FROM COUPONS GROUP BY COUNTRY_CODE ,REDEEM_STATUS";

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ExceptionObjDAO exceptionDAO;
	
	@Autowired
	DBOperationsDAO dbOperationsDAO;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean getCouponData(String couponCode) {
		boolean isThere = false;
		List<Map<String, Object>> myList = jdbcTemplate.queryForList(GET_COUPON_DATA_BY_CODE, couponCode);
		if (!myList.isEmpty()) {
			isThere = true;
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponData()","GET_COUPON_DATA_BY_CODE","Success");
		}
		else{
			isThere = false;
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponData()","GET_COUPON_DATA_BY_CODE","Failure");
		}
		return isThere;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean createCouponData(CouponData couponData) {
		boolean created = false;
		try {
			int i = jdbcTemplate.update(CREATE_COUPON_DATA,
					couponData.getCouponCode(), couponData.getCouponValue(),
					couponData.getCouponHideLocation(),
					couponData.getReedemStatus(),
					couponData.getValidityPeriod(), couponData.getValidFrom(),
					couponData.getValidTo(),
					couponData.getAvailableRedemptions(),
					couponData.getCountryCode());

			if (i > 0) {
				created = true;
			}
		} catch (DuplicateKeyException de) {
			LOGGER.log(Priority.ERROR, de);
			exceptionDAO.saveException(
					"Duplicate Key in CouponData Exception", de.getMessage(),
					"CSVBulkDAOImpl.java", "createCouponData");
		} catch (Exception e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("Coupon Data Exception",
					e.getMessage(), "CSVBulkDAOImpl.java", "createCouponData");
		}
		return created;
	}

	@Override
	public List<CouponData> getCouponTrackingData() {
		List<CouponData> couponDataList = new ArrayList<CouponData>();

		List<Map<String, Object>> myList = jdbcTemplate.queryForList(GET_TRACKING_COUPON_DATA);
		if (!myList.isEmpty()) {
			for (Map<String, Object> map : myList) {
				couponDataList.add(retrieveCouponTrackData(map));
			}
			dbOperationsDAO.createDBOperation(FILE_NAME,"getCouponTrackingData()","GET_TRACKING_COUPON_DATA","Success");
		}
		return couponDataList;
	}

	private CouponData retrieveCouponTrackData(Map<String, Object> map) {

		CouponData coupon = new CouponData();

		coupon.setCouponCount(DataRetievar.getLongValue("COUPON_COUNT", map));
		coupon.setLocation(DataRetievar.getStringValue("LOCATION", map));
		coupon.setCouponHideLocation(DataRetievar.getStringValue("LOCATION",map));
		coupon.setRedeemedLocation(DataRetievar.getStringValue("", map));
		coupon.setReedemStatus(DataRetievar.getStringValue("REDEEM_STATUS", map));
		coupon.setReedemedBy(DataRetievar.getStringValue("REDEEMED_BY", map));
		coupon.setCompletedRedemptions(DataRetievar.getLongValue("COMPLETED_REDEMPTIONS", map));
		coupon.setCountryCode(DataRetievar.getStringValue("C_CODE", map));

		return coupon;
	}

}
