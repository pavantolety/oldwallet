package com.oldwallet.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
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
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponPayment;
import com.oldwallet.model.Transaction;
import com.oldwallet.util.paypal.Configuration;

@Controller
public class CouponPaymentController {
	
	@Autowired
	SMSController smsController;
	
	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	TransactionDAO transactionDAO;
	
	private static Logger log = Logger.getLogger(CouponPaymentController.class);
	
	@RequestMapping(value="/validateCoupon", method=RequestMethod.POST)
	public void validateCoupon(ModelMap modelMap, Coupon coupon) {
		log.debug("Beginning Of Validating Coupon ::: "+coupon);
				
		if(coupon!=null && coupon.getCouponCode()!=null) {
			//User entered a coupon code.
			boolean isExists = couponDAO.isCouponExists(coupon.getCouponCode());
			if(isExists) {
				
				log.debug("Coupon Exists :::");
			Coupon validCoupon = couponDAO.getCouponByCode(coupon.getCouponCode());

			if(validCoupon!=null) {
				if(validCoupon.getAvailableRedemptions() !=0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
				//User entered a valid coupon.
				log.debug("Coupon VALID :::");
				modelMap.put("coupon", validCoupon);
				modelMap.put("action", "valid");
				modelMap.put("message", "You have entered a valid coupon.!");
				}else{
		
					modelMap.put("action", "expired");
					modelMap.put("message", "Coupon Code Expired or Event Closed.!");
				}
			} else {
				//user entered expired coupon.
				boolean isUpdated = transactionDAO.updateCoupon(validCoupon.getCouponCode());
				modelMap.put("action", "expired");
				modelMap.put("message", "Coupon Code Expired or Event Closed.!");
			}
			} else {
				log.debug("Coupon INVALID :::");
				modelMap.put("action", "invalid");
				modelMap.put("message", "Coupon Code is Expired.!");
			}
			
		} else {
			// User didn't entered any coupon.
			modelMap.put("action", "error");
			modelMap.put("message", "Please enter a coupon.");
		}
		
	}
	
	@RequestMapping(value="/valid", method=RequestMethod.POST)
	public String validCouponResponse(ModelMap modelMap, Coupon coupon) {
		System.out.println("Beginnig of ValidCoupon Response ::");
		String returnURI = "/index";
		String couponCode = coupon.getCouponCode();
		if(couponCode!= null && couponCode!= "" && couponCode.length()>4) {
			Coupon validCoupon = couponDAO.getCouponByCode(couponCode);
			if(validCoupon!=null) {
				if(validCoupon.getAvailableRedemptions() !=0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
					//User entered a valid coupon.
					System.out.println("Coupon VALID :::");
					modelMap.put("coupon", validCoupon);
					modelMap.put("action", "valid");
					modelMap.put("message", "You have entered a valid coupon.!");
					returnURI = PageView.THANKYOU;
					}else{
						modelMap.put("action", "expired");
						modelMap.put("message", "Coupon Code Expired or Event Closed.!");
						returnURI =  "error";
					}
				
				System.out.println("Coupon is Valid ::");
				
				
				modelMap.put("coupon", validCoupon);
				modelMap.put("action", "success");
				modelMap.put("message", "Valid Coupon");
			} else {
				//user entered expired coupon.
			
				modelMap.put("action", "error");
				modelMap.put("message", "Invalid Coupon");
			}
		} else {
			modelMap.put("action", "error");
			modelMap.put("message", "Invalid coupon");
		}		
		System.out.println("End of ValidCoupon Response ::");
		return returnURI;
	}
	
	@RequestMapping(value="/getCouponAmount", method=RequestMethod.POST)
	public String sendMassPayment(ModelMap modelMap, CouponPayment couponPayment, HttpSession session) {
		
		log.debug("Begining of sendMassPayment() ::::"+couponPayment.getAmount()+", "+couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO.getTransactionDetailsByEmail(couponPayment.getEmailAddress());
		String returnPage = "error";
		Coupon validCoupon = couponDAO.getCouponByCode(couponPayment.getCouponCode());

		if(validCoupon!=null) {
		if(validCoupon.getAvailableRedemptions() !=0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
		if(transactionDetails==null){

		MassPayReq req = new MassPayReq();

		List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();
		
		BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue(couponPayment.getCurrencyCode()),couponPayment.getAmount());
		
		MassPayRequestItemType item = new MassPayRequestItemType(amount);
		
		item.setReceiverEmail(couponPayment.getEmailAddress());
		massPayItem.add(item);
		
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
			//Building Transaction Object ::
			String transactionCode = UUID.randomUUID().toString().replaceAll("-", "");
			log.debug("EventId ::: "+couponPayment.getEventId());
			log.debug("CouponId ::: "+couponPayment.getCouponId());
			log.debug("CouponCode ::: "+couponPayment.getCouponCode());
			log.debug("Amount ::: "+couponPayment.getAmount());
			log.debug("Tran ::: "+transactionCode);
			
			Transaction transaction = new Transaction();
			transaction.setCouponCode(couponPayment.getCouponCode());
			transaction.setCouponId(couponPayment.getCouponId());
			transaction.setCouponValue(couponPayment.getAmount());
			transaction.setEventId(couponPayment.getEventId());
			transaction.setTransactionCode(transactionCode);
			transaction.setUserEmail(couponPayment.getEmailAddress());
			transaction.setUserMobile(couponPayment.getMobile());
			
			//Init the transaction ..
			
			boolean isTransactionInit = transactionDAO.initTransaction(transaction);
			//TODO if isTransactioninit true then only go for masspay.
			MassPayResponseType resp = service.massPay(req);
			if (resp != null) {
				modelMap.addAttribute("lastReq", service.getLastRequest());
				modelMap.addAttribute("lastResp", service.getLastResponse());
				if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					Map<Object, Object> map = new LinkedHashMap<Object, Object>();
					map.put("Ack", resp.getAck());
					modelMap.addAttribute("map", map);
					//response.sendRedirect(this.getServletContext().getContextPath()+"/Response.jsp");
					log.debug("Success :: "+resp.toString());
					Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
					transaction2.setStatus("COMPLETE");
					System.out.println("email is "+transaction2.getUserEmail() );
					transaction2.setRedeemedLocation(couponPayment.getRedeemedLocation());
					transaction2.setRedeemedLocationCode(couponPayment.getRedeemedLocationCode());
					
					Coupon coupon =  couponDAO.getCouponByCode(transaction2.getCouponCode());
					
					if(coupon!=null){
						
							if(coupon.getRedeemedBy()==null){
								transaction2.setAvailableRedemptions(coupon.getAvailableRedemptions());
								boolean isUpdated = transactionDAO.UpdateTransaction(transaction2);
							}else{
								transaction2.setAvailableRedemptions(coupon.getAvailableRedemptions());
								boolean isUpdated = transactionDAO.UpdateRedeemedTrasaction(transaction2);
							}
					
					}
				    if(validCoupon.getAvailableRedemptions() == 1){
				    	boolean isUpdated = transactionDAO.updateCoupon(validCoupon.getCouponCode());
						Transaction transaction3 = transactionDAO.getTransactionDetailsByEmail(validCoupon.getRedeemedBy());
						long referedAmount = NumberUtils.toLong(validCoupon.getCouponValue())+NumberUtils.toLong(transaction3.getCouponValue());
						transaction3.setCouponValue(referedAmount+"");
						boolean updateRef = transactionDAO.updateTransactionByEmail(transaction3);
						CouponPayment cp =  new CouponPayment();
						cp.setEmailAddress(transaction3.getUserEmail());
						cp.setCurrencyCode("USD");
						cp.setAmount(transaction3.getCouponValue());
						boolean isSuccess = sendSuperUserPayment(cp);
						System.out.println("IS-SUCCESS"+isSuccess);
				    }
					if(couponPayment.getMobile()!=null && couponPayment.getMobile().length()>4) {
					//smsController.sendSMS(modelMap, couponPayment.getMobile(),transaction2.getCouponValue(), session);
					}
					return "success";
				} else {
					modelMap.addAttribute("Error", resp.getErrors());
					Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
					transaction2.setStatus("ERROR");
					transactionDAO.UpdateTransaction(transaction2);
					log.debug(resp.getErrors().toString());
					//response.sendRedirect(this.getServletContext().getContextPath()+"/Error.jsp");
					return "emailError";
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return returnPage;
		}else{
			modelMap.put("message", "Coupon already used for this email!");
			return "emailError";
		}
		}
		}else{
			modelMap.put("message", "Coupon already used for this email!");
			return "emailError";
		}
		return returnPage;
	}
	
	public boolean sendSuperUserPayment( CouponPayment couponPayment) {
		
		log.debug("Begining of sendMassPayment() ::::"+couponPayment.getAmount()+", "+couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO.getTransactionDetailsByEmail(couponPayment.getEmailAddress());
		boolean isSent = false;
		Coupon validCoupon = couponDAO.getCouponByCode(couponPayment.getCouponCode());

		if(validCoupon!=null) {
		if(validCoupon.getAvailableRedemptions() !=0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
		if(transactionDetails==null){

		MassPayReq req = new MassPayReq();

		List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();
		
		BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue(couponPayment.getCurrencyCode()),couponPayment.getAmount());
		
		MassPayRequestItemType item = new MassPayRequestItemType(amount);
		
		item.setReceiverEmail(couponPayment.getEmailAddress());
		massPayItem.add(item);
		
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
			//Building Transaction Object ::
			String transactionCode = UUID.randomUUID().toString().replaceAll("-", "");
			log.debug("EventId ::: "+couponPayment.getEventId());
			log.debug("CouponId ::: "+couponPayment.getCouponId());
			log.debug("CouponCode ::: "+couponPayment.getCouponCode());
			log.debug("Amount ::: "+couponPayment.getAmount());
			log.debug("Tran ::: "+transactionCode);
			
			Transaction transaction = new Transaction();
			transaction.setCouponCode(couponPayment.getCouponCode());
			transaction.setCouponId(couponPayment.getCouponId());
			transaction.setCouponValue(couponPayment.getAmount());
			transaction.setEventId(couponPayment.getEventId());
			transaction.setTransactionCode(transactionCode);
			transaction.setUserEmail(couponPayment.getEmailAddress());
			transaction.setUserMobile(couponPayment.getMobile());
			
			//Init the transaction ..
			
			boolean isTransactionInit = transactionDAO.initTransaction(transaction);
			//TODO if isTransactioninit true then only go for masspay.
			MassPayResponseType resp = service.massPay(req);
			if (resp != null) {
				
				if (resp.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					Map<Object, Object> map = new LinkedHashMap<Object, Object>();
					map.put("Ack", resp.getAck());
				
					//response.sendRedirect(this.getServletContext().getContextPath()+"/Response.jsp");
					log.debug("Success :: "+resp.toString());
				
					isSent = true;
				} else {
	
					Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
					transaction2.setStatus("ERROR");
					transactionDAO.UpdateTransaction(transaction2);
					log.debug(resp.getErrors().toString());
					//response.sendRedirect(this.getServletContext().getContextPath()+"/Error.jsp");
					isSent = false;
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSent;
		}
		}
		}
		return isSent;
	}
}
