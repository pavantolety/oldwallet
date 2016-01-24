package com.oldwallet.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
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

import com.oldwallet.config.SystemParams;
import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.ExceptionObjDAO;
import com.oldwallet.model.AdminSession;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponStatistics;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.MassPay;
import com.oldwallet.util.paypal.Configuration;

@Controller
public class OldwalletController {

	private static final Logger LOGGER = Logger.getLogger(OldwalletController.class);

	AdminSession adminSession = null;
	
	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	ExceptionObjDAO exceptionDAO;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard";
	}

	@RequestMapping(value = "/thankYou", method = RequestMethod.GET)
	public String thankYou() {
		return PageView.THANKYOU;
	}

	@RequestMapping(value = "/massPay", method = RequestMethod.GET)
	public String massPay(HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.MASSPAY;
		}
		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/trackCoupons", method = RequestMethod.GET)
	public String trackCoupons(HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.TRACKCOUPONS;
		}
		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminHome(HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.ADMINHOME;
		}
		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/manageCoupons", method = RequestMethod.GET)
	public String manageCoupons(ModelMap modelMap, HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			List<Coupon> couponList = couponDAO.getCouponData();
			if (!couponList.isEmpty()) {
				LOGGER.debug("Going to data table :::");
				modelMap.put("couponList", couponList);
			}
			return PageView.MANAGECOUPONS;
		}
		return PageView.ADMINLOGIN;
	}

	@RequestMapping(value = "/couponStats", method = RequestMethod.GET)
	public String couponStats(ModelMap modelMap, HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			CouponStatistics cs1 = couponDAO.getTotalCouponCount();
			CouponStatistics cs2 = couponDAO.getRedeemedCount();
			CouponStatistics cs3 = couponDAO.getTotalCouponAmount();
			CouponStatistics cs4 = couponDAO.getReedmedAmount();
			if (cs1 != null) {
				modelMap.put("couponCount", cs1.getTotalCouponsCount());
			}
			if (cs2 != null) {
				modelMap.put("redeemedCount", cs2.getRedeemedCouponCount());
			}
			if (cs3 != null) {
				modelMap.put("couponAmount", cs3.getTotalCouponAmount());
			}
			if (cs4 != null) {
				long percentage = Math.round((cs4.getTotalRedeemedAmount() / cs3.getTotalCouponAmount()) * 100);
				DecimalFormat df = new DecimalFormat("#.00");
				String percentageVal = df.format(percentage);
				modelMap.put("redeemedAmount", cs4.getTotalRedeemedAmount());
				modelMap.put("percentageVal", percentageVal);
			}
			return PageView.COUPONSTATS;
		}
		return PageView.ADMINLOGIN;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/couponStatsFor", method = RequestMethod.GET)
	public String couponStatsFor(ModelMap modelMap) {
		List<CouponStatistics> csList = couponDAO.getCouponDataByReedeemStatus();
		JSONArray list = new JSONArray();
		if (!csList.isEmpty()) {
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

	@RequestMapping(value = "/createCoupon", method = RequestMethod.GET)
	public String createCoupons(HttpSession session) {
		adminSession = (AdminSession) session.getAttribute("adminSession");
		if (adminSession != null) {
			return PageView.CREATECOUPONS;
		}
		return PageView.ADMINLOGIN;
	}
	
	@RequestMapping(value = "/fundsManagement", method = RequestMethod.GET)
	public String fundsManagement(ModelMap modelMap) {
		  
		FundAllocation fundAllocationData   = couponDAO.getFundData();
	    FundAllocation fundAllocation = couponDAO.getFundAllocationData();
	    
		if(fundAllocationData!=null){
			modelMap.put("totalCount",fundAllocationData.getTotalCouponCount());
			modelMap.put("remainCount",fundAllocationData.getRedeemedCount());
			System.out.println("ava>>>>>>>>>>>>"+fundAllocationData.getRedeemedCount());
			if(fundAllocationData.getRedeemedCount()==""){
				modelMap.put("remainCount",fundAllocationData.getTotalCouponCount());
			}else{
				modelMap.put("remainCount",fundAllocationData.getRedeemedCount());
			}
			if(fundAllocation!=null){
				System.out.println("alocal1"+fundAllocation.getTotalCouponValue());
				System.out.println("alocal2"+fundAllocation.getTotalFund());
				if(fundAllocation.getTotalCouponValue()!=0){
					modelMap.put("totalValue", fundAllocation.getTotalCouponValue());
					modelMap.put("totalFund", fundAllocation.getTotalFund());
					double value = (fundAllocation.getTotalFund()-fundAllocation.getTotalCouponValue());
					if(value==fundAllocation.getTotalFund()){
						modelMap.put("remaining", 0);
					}else{
					modelMap.put("remaining", (fundAllocation.getTotalFund()-fundAllocation.getTotalCouponValue()));
					}
				   }else {
					modelMap.put("totalValue", fundAllocation.getTotalCouponValue());
					modelMap.put("totalFund", fundAllocation.getTotalFund());
					double value = (fundAllocation.getTotalFund()-fundAllocation.getTotalCouponValue());
					if(value==fundAllocation.getTotalFund()){
						modelMap.put("remaining", 0);
					}else{
					modelMap.put("remaining", (fundAllocation.getTotalFund()-fundAllocation.getTotalCouponValue()));
					}
				}
			}
		}else{
			modelMap.put("totalCount",0);
		}
		
		return PageView.FUNDSMANAGEMENT;
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
			return "/index";
		}

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/sendMassPayment", method = RequestMethod.POST)
	public String sendMassPayment(ModelMap modelMap, MassPay massPay) {
		LOGGER.debug("Begining of sendMassPayment() ::::"+ massPay.getAmount1() + ", " + massPay.getEmailAddress1());
		System.setProperty("Dhttps.protocols", "TLSv1.1,TLSv1.2");
		String returnPage = "error";
		MassPayReq req = new MassPayReq();

		List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

		BasicAmountType amount1 = null;
		if (massPay.getCurrencyCode1() != null && SystemParams.MASS_PAY_DEFAULT_CURRENCY_CODE.equalsIgnoreCase(massPay.getCurrencyCode1())) {
			amount1 = new BasicAmountType(CurrencyCodeType.fromValue(massPay.getCurrencyCode1()), massPay.getAmount1());
		}
		BasicAmountType amount2 = null;
		if (massPay.getCurrencyCode1() != null&& SystemParams.MASS_PAY_DEFAULT_CURRENCY_CODE.equalsIgnoreCase(massPay.getCurrencyCode1())) {
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
		if (!massPayItem.isEmpty()) {
			MassPayRequestType reqType = new MassPayRequestType(massPayItem);
			reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
			req.setMassPayRequest(reqType);
			Map<String, String> configurationMap = Configuration.getAcctAndConfig();

			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

			try {
				MassPayResponseType resp = service.massPay(req);
				if (resp != null) {
					modelMap.addAttribute("lastReq", service.getLastRequest());
					modelMap.addAttribute("lastResp", service.getLastResponse());
					if ("SUCCESS".equalsIgnoreCase(resp.getAck().toString())) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						modelMap.addAttribute("map", map);
						LOGGER.debug("Success :: " + resp.toString());
						return "success";
					} else {
						modelMap.addAttribute("Error", resp.getErrors());
						LOGGER.debug(resp.getErrors().toString());
						return "error";
					}
				}

			} catch (Exception e) {
				LOGGER.log(Priority.ERROR, e);
				exceptionDAO.saveException("MassPay Exception",e.getMessage(), "OldWalletController.java","sendMassPayment");
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
