package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.UserLogin;
import com.oldwallet.util.DataRetievar;

@Repository
public class UserLoginDAOImpl implements UserLoginDAO{
	
	public static final String GET_USER_DETAILS = "SELECT ID, EMAIL_ADDRESS, PASSWORD FROM USER_LOGIN WHERE EMAIL_ADDRESS=?";

	private static final Logger LOGGER = Logger
			.getLogger(AdminLoginDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public UserLogin getUserByEmailAddress(String emailAddress) {
		LOGGER.debug("Beginning of getAdminByEmailAddress ::");
		List<UserLogin> userDetailsList = new ArrayList<UserLogin>();
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(
				GET_USER_DETAILS, emailAddress);
		if (!userList.isEmpty()) {
			for (Map<String, Object> map : userList) {
				userDetailsList.add(retrieveUser(map));
			}
			return userDetailsList.get(0);
		} else {
			return null;
		}

	}

	private UserLogin retrieveUser(Map<String, Object> map) {

		UserLogin userLogin = new UserLogin();

		userLogin.setEmailAddress(DataRetievar.getStringValue("EMAIL_ADDRESS",map));
		userLogin.setId(DataRetievar.getLongValue("ID", map));

		return userLogin;
	}
}