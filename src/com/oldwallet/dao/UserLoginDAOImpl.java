package com.oldwallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.config.SystemParams;
import com.oldwallet.model.UserLogin;
import com.oldwallet.util.DataRetievar;
import com.oldwallet.util.EncryptCouponUtil;
import com.oldwallet.dao.DBOperationsDAO;

@Repository
public class UserLoginDAOImpl implements UserLoginDAO{
	
	public final static String FILE_NAME="UserLogin";
	
	public static final String GET_USER_DETAILS = "SELECT ID, EMAIL_ADDRESS, PASSWORD FROM USER_LOGIN WHERE EMAIL_ADDRESS=?";
 
	public static final String CREATE_ADMIN_USER = "INSERT INTO ADMIN_LOGIN (EMAIL_ADDRESS, PASSWORD) VALUES(?,?)";
	
	private static final Logger LOGGER = Logger
			.getLogger(AdminLoginDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	DBOperationsDAO dbOperationsDAO;

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
			dbOperationsDAO.createDBOperation(FILE_NAME,"getUserByEmailAddress()","GET_USER_DETAILS","Success");
			return userDetailsList.get(0);
		} else {
			dbOperationsDAO.createDBOperation(FILE_NAME,"getUserByEmailAddress()","GET_USER_DETAILS","Failure");
			return null;
		}

	}

	private UserLogin retrieveUser(Map<String, Object> map) {

		UserLogin userLogin = new UserLogin();

		userLogin.setEmailAddress(DataRetievar.getStringValue("EMAIL_ADDRESS",map));
		userLogin.setId(DataRetievar.getStringValue("ID", map));

		return userLogin;
	}

	@Override
	public boolean createAdminUser() {
	boolean isCreated  = false;
	
	String getGeneratedSecuredEmailHash =EncryptCouponUtil.enccd(SystemParams.ADMIN_EMAIL);
	String getGeneratedSecuredPasswordHash = EncryptCouponUtil.enccd(SystemParams.ADMIN_PASSWORD);
	     int i  = jdbcTemplate.update(CREATE_ADMIN_USER,getGeneratedSecuredEmailHash,getGeneratedSecuredPasswordHash);
	     if(i>0){
	    	 isCreated = true;
	    	 dbOperationsDAO.createDBOperation(FILE_NAME,"createAdminUser()","CREATE_ADMIN_USER","Success");
	     }
	     else{
	    	 dbOperationsDAO.createDBOperation(FILE_NAME,"createAdminUser()","CREATE_ADMIN_USER","Failure");
	    	 isCreated = false;
	     }
		return isCreated;
	}
}
