package com.oldwallet.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import urn.ebay.api.PayPalAPI.MassPayReq;
import urn.ebay.api.PayPalAPI.MassPayRequestItemType;
import urn.ebay.api.PayPalAPI.MassPayRequestType;
import urn.ebay.api.PayPalAPI.MassPayResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ReceiverInfoCodeType;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.MassPay;
import com.oldwallet.util.paypal.Configuration;

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
		
		@RequestMapping(value="/massPay", method=RequestMethod.GET)
		public String massPay(ModelMap modelMap) {
			return PageView.MASSPAY;
		}
		
		@RequestMapping(value="/trackCoupons", method=RequestMethod.GET)
		public String trackCoupons(ModelMap modelMap) {
			return PageView.TRACKCOUPONS;
		}
		
		@RequestMapping(value="/trackRedemptions", method=RequestMethod.GET)
		public String trackRedemption(ModelMap modelMap) {
			return PageView.TRACKREDEMPTIONS;
		}
		
		@RequestMapping(value="/adminHome", method=RequestMethod.GET)
		public String adminHome(ModelMap modelMap) {
			return PageView.ADMINHOME;
		}
		
		@RequestMapping(value="/upload", method=RequestMethod.GET)
		public String upload(ModelMap modelMap) {
			return PageView.UPLOAD;
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
		
		@RequestMapping(value="/sendMassPayment", method=RequestMethod.POST)
		public String sendMassPayment(ModelMap modelMap, MassPay massPay, HttpSession session) {
			System.out.println("Begining of sendMassPayment() ::::"+massPay.getAmount1()+", "+massPay.getEmailAddress1());
			String returnPage = "error";
			MassPayReq req = new MassPayReq();

			List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();
			
			BasicAmountType amount1 = new BasicAmountType(CurrencyCodeType.fromValue(massPay.getCurrencyCode1()),massPay.getAmount1());
			BasicAmountType amount2 = new BasicAmountType(CurrencyCodeType.fromValue(massPay.getCurrencyCode2()),massPay.getAmount2());
			
			MassPayRequestItemType item1 = new MassPayRequestItemType(amount1);
			MassPayRequestItemType item2 = new MassPayRequestItemType(amount2);
			
			item1.setReceiverEmail(massPay.getEmailAddress1());
			item2.setReceiverEmail(massPay.getEmailAddress2());
			
			massPayItem.add(item1);
			massPayItem.add(item2);
			
			MassPayRequestType reqType = new MassPayRequestType(massPayItem);
			reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
			req.setMassPayRequest(reqType);
			
			// Configuration map containing signature credentials and other required configuration.
			// For a full list of configuration parameters refer in wiki page.
			// (https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters)
			Map<String,String> configurationMap =  Configuration.getAcctAndConfig();
			
			// Creating service wrapper object to make an API call by loading configuration map.
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			
			try {
				MassPayResponseType resp = service.massPay(req);
				if (resp != null) {
					modelMap.addAttribute("lastReq", service.getLastRequest());
					modelMap.addAttribute("lastResp", service.getLastResponse());
					if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						modelMap.addAttribute("map", map);
						//response.sendRedirect(this.getServletContext().getContextPath()+"/Response.jsp");
						System.out.println("Success :: "+resp.toString());
						return "success";
					} else {
						modelMap.addAttribute("Error", resp.getErrors());
						System.out.println(resp.getErrors().toString());
						//response.sendRedirect(this.getServletContext().getContextPath()+"/Error.jsp");
						return "error";
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return returnPage;
		}
		
}
