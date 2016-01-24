package com.oldwallet.dao;

import com.oldwallet.model.UserLogin;

public interface UserLoginDAO {
	
	UserLogin getUserByEmailAddress(String emailAddress);

	boolean createAdminUser();
}
