package com.oldwallet.dao;

import com.oldwallet.model.AdminLogin;

public interface AdminLoginDAO {

	AdminLogin getAdminByEmailAddress(String emailAddress);

}
