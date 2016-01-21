package com.oldwallet.controllers;

import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;
import com.oldwallet.model.UserLogin;
import com.oldwallet.model.UserSession;

public final class AuthenticationHelper {

	private AuthenticationHelper() {

	}

	public static AdminSession populateAdminSession(AdminLogin adminLogin) {
		AdminSession adminSession = new AdminSession();
		adminSession.setId(adminLogin.getId());
		adminSession.setEmailAddress(adminLogin.getEmailAddress());

		return adminSession;
	}
	
	public static UserSession populateUserSession(UserLogin userLogin) {
		UserSession userSession = new UserSession();
		
		userSession.setId(userLogin.getId());
		userSession.setEmailAddress(userLogin.getEmailAddress());
		userSession.setCouponCode(userLogin.getCouponCode());
		userSession.setAmount(userLogin.getAmount());

		return userSession;
	}

}