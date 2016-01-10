package com.oldwallet.controllers;

import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;


public class AuthenticationHelper {
	
	
	public static AdminSession populateAdminSession(AdminLogin adminLogin, AdminLogin validAdmin) {
		AdminSession adminSession=new AdminSession();
		adminSession.setId(adminLogin.getId());
		adminSession.setEmailAddress(adminLogin.getEmailAddress());
				
		return adminSession;
	}

}
