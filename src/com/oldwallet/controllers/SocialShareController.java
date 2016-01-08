package com.oldwallet.controllers;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocialShareController {
	
	@RequestMapping(value="/generateId", method = RequestMethod.GET)
	public String generateId(ModelMap modelMap) {
		return null;
	}
}
