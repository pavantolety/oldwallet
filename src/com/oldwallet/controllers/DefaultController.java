package com.oldwallet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.constraints.PageView;

@Controller
public class DefaultController {
		
		@RequestMapping(value = { "/", "/index" })
		public String index(ModelMap modelMap) {				
			return "index";
		}
				
		@RequestMapping(value="/thankYou", method=RequestMethod.GET)
		public String thankYou(ModelMap modelMap) {
			return PageView.THANKYOU;
		}
		
		@RequestMapping(value="/terms", method=RequestMethod.GET)
		public String terms(ModelMap modelMap) {
			return PageView.TERMSOFUSE;
		}
}