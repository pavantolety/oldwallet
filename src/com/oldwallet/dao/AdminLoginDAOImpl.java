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
import com.oldwallet.util.DataRetievar;

@Repository
public class AdminLoginDAOImpl implements AdminLoginDAO {

	public static final String GET_ADMIN_DETAILS = "SELECT ID, EMAIL_ADDRESS, PASSWORD FROM ADMIN_LOGIN WHERE EMAIL_ADDRESS=?";
	
	private static final Logger LOGGER = Logger.getLogger(AdminLoginDAOImpl.class);

	private JdbcTemplate jdbcTemplate;	

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public AdminLogin getAdminByEmailAddress(String emailAddress) {
		LOGGER.debug("Beginning of getAdminByEmailAddress ::");
		List<AdminLogin> adminDetailsList = new ArrayList<AdminLogin>();
		List<Map<String, Object>> adminList = jdbcTemplate.queryForList(GET_ADMIN_DETAILS, emailAddress);
		if (!adminList.isEmpty()) {
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

		adminLogin.setEmailAddress(DataRetievar.getStringValue("EMAIL_ADDRESS",map));
		adminLogin.setId(DataRetievar.getLongValue("ID", map));

		return adminLogin;
	}
}
