package com.oldwallet.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.oldwallet.model.AdminLogin;
import com.oldwallet.model.AdminSession;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponStatistics;
import com.oldwallet.model.MassPay;
import com.oldwallet.util.paypal.Configuration;

@Controller
public class OldwalletController {

	@Autowired
	CouponDAO couponDAO;
	
	private static final Logger LOGGER = Logger.getLogger(OldwalletController.class);

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard";
	}

	@RequestMapping(value = "/redeem", method = RequestMethod.GET)
	public String redeem() {
		return PageView.REDEEM;
	}

	@RequestMapping(value = "/thankYou", method = RequestMethod.GET)
	public String thankYou() {
		return PageView.THANKYOU;
	}

	@RequestMapping(value = "/massPay", method = RequestMethod.GET)
	public String massPay(AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.MASSPAY;
		}
		return "/adminLogin";
	}

	@RequestMapping(value = "/trackCoupons", method = RequestMethod.GET)
	public String trackCoupons(AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.TRACKCOUPONS;
		}
		return "/adminLogin";
	}

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminHome(AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.ADMINHOME;
		}
		return "/adminLogin";
	}

	@RequestMapping(value = "/manageCoupons", method = RequestMethod.GET)
	public String manageCoupons(ModelMap modelMap, AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			List<Coupon> couponList = couponDAO.getCouponData();
			if (couponList != null && couponList.size() > 0) {
				LOGGER.debug("Going to data table :::");
				modelMap.put("couponList", couponList);
			}
			return PageView.MANAGECOUPONS;
		}
		return "/adminLogin";
	}

	@RequestMapping(value = "/couponStats", method = RequestMethod.GET)
	public String couponStats(ModelMap modelMap, AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			CouponStatistics cs1 = couponDAO.getTotalCouponCount();
			CouponStatistics cs2 = couponDAO.getRedeemedCount();
			CouponStatistics cs3 = couponDAO.getTotalCouponAmount();
			CouponStatistics cs4 = couponDAO.getReedmedAmount();
			if (cs1 != null) {
				long percentage = Math.round((cs4.getTotalRedeemedAmount() / cs3.getTotalCouponAmount()) * 100);
				DecimalFormat df = new DecimalFormat("#.00");
				String percentageVal = df.format(percentage);
				modelMap.put("couponCount", cs1.getTotalCouponsCount());
				modelMap.put("redeemedCount", cs2.getRedeemedCouponCount());
				modelMap.put("couponAmount", cs3.getTotalCouponAmount());
				modelMap.put("redeemedAmount", cs4.getTotalRedeemedAmount());
				modelMap.put("percentageVal", percentageVal);
			}
			return PageView.COUPONSTATS;
		}
		return "/adminLogin";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/couponStatsFor", method = RequestMethod.GET)
	public String couponStatsFor(ModelMap modelMap, AdminLogin adminLogin, HttpSession session) {
		List<CouponStatistics> csList = couponDAO.getCouponDataByReedeemStatus();
		JSONArray list = new JSONArray();
		if (csList.size() > 0) {
			for (CouponStatistics cs : csList) {
				JSONObject obj = new JSONObject();
				obj.put("value", cs.getTotalCouponsCount());
				obj.put("name", cs.getRedeemStatus());
				list.add(obj);
			}
			modelMap.put("list", list);
		}
		return "";
	}

	@RequestMapping(value = "/downloadData", method = RequestMethod.GET)
	public String downloadData(AdminLogin adminLogin, HttpSession session) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.DOWNLOADDATA;
		}
		return "/adminLogin";
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String facebook() {
		return PageView.FACEBOOK;
	}

	@RequestMapping(value = "/validateCoupon", method = RequestMethod.GET)
	public String validateCoupon(ModelMap modelMap, Coupon coupon) {

		LOGGER.debug("Coupon Code::::" + coupon.getCouponCode());

		Coupon couponCode = couponDAO.getCouponByCode(coupon.getCouponCode());
		if (couponCode != null) {
			LOGGER.debug("Going to the ThankYou ::: ");
			return PageView.THANKYOU;

		} else {
			modelMap.put("status", "failure");
			modelMap.put("error", "Invalid Coupon");
			modelMap.put("message", "Please use valid coupon code to redeem...");
			return PageView.REDEEM;
		}

	}

	@RequestMapping(value = "/sendMassPayment", method = RequestMethod.POST)
	public String sendMassPayment(ModelMap modelMap, MassPay massPay, HttpSession session) {
		LOGGER.debug("Begining of sendMassPayment() ::::"+ massPay.getAmount1() + ", " + massPay.getEmailAddress1());
		String returnPage = "error";
		MassPayReq req = new MassPayReq();

		List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

		BasicAmountType amount1 = null;
		if (massPay.getCurrencyCode1() != null && massPay.getCurrencyCode1().equalsIgnoreCase("USD")) {
			amount1 = new BasicAmountType(CurrencyCodeType.fromValue(massPay.getCurrencyCode1()), massPay.getAmount1());
		}
		BasicAmountType amount2 = null;
		if (massPay.getCurrencyCode1() != null && massPay.getCurrencyCode1().equalsIgnoreCase("USD")) {
			amount2 = new BasicAmountType(CurrencyCodeType.fromValue(massPay.getCurrencyCode1()), massPay.getAmount1());
		}
		MassPayRequestItemType item1 = null;
		MassPayRequestItemType item2 = null;
		if (amount1 != null) {
			item1 = new MassPayRequestItemType(amount1);
			if (massPay.getEmailAddress1() != null && massPay.getEmailAddress1().length() > 1) {
				item1.setReceiverEmail(massPay.getEmailAddress1());
				massPayItem.add(item1);
			}
		}
		if (amount2 != null) {
			item2 = new MassPayRequestItemType(amount2);
			if (massPay.getEmailAddress2() != null && massPay.getEmailAddress2().length() > 1) {
				item2.setReceiverEmail(massPay.getEmailAddress2());
				massPayItem.add(item2);
			}
		}
		if (massPayItem.size() > 0) {
			MassPayRequestType reqType = new MassPayRequestType(massPayItem);
			reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
			req.setMassPayRequest(reqType);

			// Configuration map containing signature credentials and other
			// required configuration.
			// For a full list of configuration parameters refer in wiki page.
			// (https://github.com/paypal/sdk-core-java/wiki/SDK-Configuration-Parameters)
			Map<String, String> configurationMap = Configuration
					.getAcctAndConfig();

			// Creating service wrapper object to make an API call by loading
			// configuration map.
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
						// response.sendRedirect(this.getServletContext().getContextPath()+"/Response.jsp");
						LOGGER.debug("Success :: " + resp.toString());
						return "success";
					} else {
						modelMap.addAttribute("Error", resp.getErrors());
						LOGGER.debug(resp.getErrors().toString());
						// response.sendRedirect(this.getServletContext().getContextPath()+"/Error.jsp");
						return "error";
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			modelMap.put("action", "Error");
			modelMap.put("message", "Unable to process your request");
		}
		return returnPage;
	}

	@RequestMapping(value = { "/emailError" }, method = RequestMethod.GET)
	public String emailError() {
		return "emailError";
	}
}
