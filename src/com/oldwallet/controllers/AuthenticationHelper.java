package com.oldwallet.controllers;

import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;

public final class AuthenticationHelper {

	private AuthenticationHelper() {

	}

	public static AdminSession populateAdminSession(AdminLogin adminLogin) {
		AdminSession adminSession = new AdminSession();
		adminSession.setId(adminLogin.getId());
		adminSession.setEmailAddress(adminLogin.getEmailAddress());

		return adminSession;
	}

}
