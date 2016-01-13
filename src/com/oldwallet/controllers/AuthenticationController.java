package com.oldwallet.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.dao.AdminLoginDAO;
import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;

@Controller
public class AuthenticationController {

	@Autowired
	AdminLoginDAO adminLoginDAO;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogin(ModelMap modelMap) {
		return "/adminLogin";
	}

	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, AdminLogin adminLogin,
			HttpSession session) {

		session.removeAttribute("adminSession");

		return "/adminLogin";
	}

	@RequestMapping(value = "/adminSubmit", method = { RequestMethod.POST, RequestMethod.GET })
	public String getAdminHome(ModelMap modelMap, AdminLogin adminLogin, HttpSession session) {

		AdminLogin validAdmin = adminLoginDAO.getAdminByEmailAddress(adminLogin.getEmailAddress());
		AdminSession adminSession = AuthenticationHelper.populateAdminSession(adminLogin, validAdmin);
		session.setAttribute("adminSession", adminSession);
		if (validAdmin != null) {
			return "/adminHome";
		}
		modelMap.put("status", "error");
		modelMap.put("message", "Please give valid Credentials");

		return "/adminLogin";
	}
}
