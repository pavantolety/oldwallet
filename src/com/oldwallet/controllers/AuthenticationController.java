package com.oldwallet.controllers;

import javax.servlet.http.HttpSession;

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

	@Autowired
	AdminLoginDAO adminLoginDAO;
	
	@Autowired
	UserLoginDAO userLoginDAO;

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

		AdminLogin validAdmin = adminLoginDAO.getAdminByEmailAddress(adminLogin.getEmailAddress());
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
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin() {
		return PageView.USERLOGIN;
	}
	
	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public String userlogout(HttpSession session) {

		session.removeAttribute("userSession");

		return PageView.USERLOGIN;
	}
	
	@RequestMapping(value = "/userSubmit", method = { RequestMethod.POST,RequestMethod.GET })
	public String getUserHome(ModelMap modelMap, UserLogin userLogin,HttpSession session) {

		String returnURI = PageView.USERLOGIN;

		UserLogin validUser = userLoginDAO.getUserByEmailAddress(userLogin.getEmailAddress());
		if (validUser != null) {
			returnURI = "/index";
			UserSession userSession = AuthenticationHelper.populateUserSession(userLogin);
			session.setAttribute("userSession", userSession);
		} else {
			modelMap.put("status", "error");
			modelMap.put("message", "Please give valid Credentials");
		}
		return returnURI;
	}
	
}
