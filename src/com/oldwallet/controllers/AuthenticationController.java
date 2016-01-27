package com.oldwallet.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.AdminLoginDAO;
import com.oldwallet.dao.UserLoginDAO;
import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;
import com.oldwallet.model.UserLogin;
import com.oldwallet.model.UserSession;

@Controller
public class AuthenticationController {
	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);
	@Autowired
	AdminLoginDAO adminLoginDAO;
	
	@Autowired
	UserLoginDAO userLoginDAO;

	
	
	@RequestMapping(value="/createAdmin")
	public void createAdmin(){
		boolean isCreated = userLoginDAO.createAdminUser();
		
		LOGGER.info("Amdin>>>>>>>>>"+isCreated);
	}
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogin() {
		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.removeAttribute("adminSession");

		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/adminSubmit", method = { RequestMethod.POST,RequestMethod.GET })
	public String getAdminHome(ModelMap modelMap, AdminLogin adminLogin, HttpSession session) {

		String returnURI = PageView.ADMINLOGIN;

		AdminLogin validAdmin = adminLoginDAO.getAdminByEmailAddress(adminLogin.getEmailAddress(),adminLogin.getPassword());
		if (validAdmin != null) {
			returnURI = PageView.ADMINHOME;
			AdminSession adminSession = AuthenticationHelper.populateAdminSession(adminLogin);
			session.setAttribute("adminSession", adminSession);
		} else {
			modelMap.put("status", "error");
			modelMap.put("message", "Please give valid Credentials");
		}
		return returnURI;
	}
	
}
