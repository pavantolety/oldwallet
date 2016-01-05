package com.oldwallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.model.Coupon;

@Controller
public class OldwalletController {
		
		@Autowired
		CouponDAO couponDAO;
		
		@RequestMapping(value = { "/", "/index" })
		public String index(ModelMap modelMap) {
				
			return "index";
		}
		
		@RequestMapping(value={"/dashboard"}, method=RequestMethod.GET)
		public String dashboard(ModelMap modelMap) {
			return "dashboard";
		}
		
		@RequestMapping(value="/redeem", method=RequestMethod.GET)
		public String redeem(ModelMap modelMap) {
			return PageView.REDEEM;
		}
		
		@RequestMapping(value="/thankYou", method=RequestMethod.GET)
		public String thankYou(ModelMap modelMap) {
			return PageView.THANKYOU;
		}
		
		@RequestMapping(value="/terms", method=RequestMethod.GET)
		public String terms(ModelMap modelMap) {
			return PageView.TERMSOFUSE;
		}
		
		@RequestMapping(value="/validateCoupon", method=RequestMethod.GET)
		public String validateCoupon(ModelMap modelMap,Coupon coupon) {
			
			System.out.println("Coupon Code::::" +coupon.getCouponCode());
			
			Coupon couponCode = couponDAO.getCouponByCode(coupon.getCouponCode());
			if (couponCode!=null){
				System.out.println("Going to the ThankYou ::: ");
				return PageView.THANKYOU;
			
			}else {
				modelMap.put("status","failure");
				modelMap.put("error", "Invalid Coupon");
				modelMap.put("message", "Please use valid coupon code to redeem...");
				return PageView.REDEEM;
			}
			
		}
		
}
