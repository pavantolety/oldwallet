package com.oldwallet.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
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
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponPayment;
import com.oldwallet.model.Transaction;
import com.oldwallet.model.UserToken;
import com.oldwallet.util.AuthenticationUtils;
import com.oldwallet.util.SMSUtil;
import com.oldwallet.util.paypal.Configuration;

@Controller
public class CouponPaymentController {
	
	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	TransactionDAO transactionDAO;
	
	private static Logger log = Logger.getLogger(CouponPaymentController.class);
	
	
	@RequestMapping(value="/saveCouponData", method=RequestMethod.POST)
	public void saveCouponData(ModelMap modelMap, Coupon coupon) throws ParseException {
		log.debug("Beginning Of Validating Coupon ::: "+coupon);
				
		if(coupon!=null ) {
			  SimpleDateFormat format1 =  new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		      SimpleDateFormat format2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      coupon.setValidFrom(format2.format(format1.parse(coupon.getValidFrom())));
		      coupon.setValidTo(format2.format(format1.parse(coupon.getValidTo())));
		      boolean  isUpdated = couponDAO.updateCouponData(coupon);
		      if(isUpdated){
		    	  modelMap.put("status", "success");
		    	 
		      }else{
		    	  modelMap.put("status", "failure");
		      }
			
		}
		
	}
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
				if(validCoupon.getAvailableRedemptions() >0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
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
				//boolean isUpdated = transactionDAO.updateCoupon(validCoupon.getCouponCode());
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
	@RequestMapping(value = "/redeemedKey" , method = RequestMethod.GET)
	public String getCouponValidation(ModelMap  modelMap ,Coupon coupon,HttpServletRequest request){
		System.out.println("Beginnig of ValidCoupon Response ::");
		String returnURI = "/index";
	        UserToken userToken = couponDAO.getRedeemKey(coupon.getRedeemKey());
	        if(userToken!=null){
			Coupon validCoupon = couponDAO.getCouponByCode(userToken.getCouponCode());
			if(validCoupon!=null) {
				if(validCoupon.getAvailableRedemptions() >0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
					//User entered a valid coupon.
					System.out.println("Coupon VALID :::");
					modelMap.put("coupon", validCoupon);
					modelMap.put("action", "valid");
					modelMap.put("message", "You have entered a valid coupon.!");
					return  PageView.THANKYOU;
					}else{
						modelMap.put("action", "expired");
						modelMap.put("message", "Coupon Code Expired or Event Closed.!");
						
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
	        }else{
	        	modelMap.put("action", "error");
				modelMap.put("message", "Invalid Coupon");
	        }
		
		return  PageView.THANKYOU;
	}
	@RequestMapping(value="/valid", method={RequestMethod.POST,RequestMethod.GET})
	public String validCouponResponse(ModelMap modelMap, Coupon coupon,HttpServletRequest request) {
		System.out.println("Beginnig of ValidCoupon Response ::");
		String returnURI = "/index";
		String couponCode = coupon.getCouponCode();
		if(couponCode!= null && couponCode!= "" && couponCode.length()>4) {
			Coupon validCoupon = couponDAO.getCouponByCode(couponCode);
			if(validCoupon!=null) {
				if(validCoupon.getAvailableRedemptions() >0 && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
					//User entered a valid coupon.
					System.out.println("Coupon VALID :::");
					modelMap.put("coupon", validCoupon);
					modelMap.put("action", "valid");
					modelMap.put("message", "You have entered a valid coupon.!");
					return  PageView.THANKYOU;
					}else{
						modelMap.put("action", "expired");
						modelMap.put("message", "Coupon Code Expired or Event Closed.!");
						
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
		System.out.println("End of ValidCoupon Response :>>>>>>:"+request.getMethod());
		if(request.getMethod()=="GET"){
			return "index";
		}
		return  PageView.THANKYOU;
	}
	
	@RequestMapping(value="/getCouponAmount", method=RequestMethod.POST)
	public void sendMassPayment(ModelMap modelMap, CouponPayment couponPayment, HttpSession session) {
		
		log.debug("Begining of sendMassPayment() ::::"+couponPayment.getAmount()+", "+couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO.getTransactionDetailsByEmail(couponPayment.getEmailAddress(),NumberUtils.toLong(couponPayment.getEventId()));
		
		Coupon validCoupon = couponDAO.getCouponByCode(couponPayment.getCouponCode());

		if(validCoupon!=null) {
		if(validCoupon.getCompletedRedemptions()<validCoupon.getAvailableRedemptions() && validCoupon.getRedeemStatus().equalsIgnoreCase("NEW")){
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
					transaction2.setLatitude(couponPayment.getLatitude());
					transaction2.setLongitude(couponPayment.getLongitude());
					
					Coupon coupon =  couponDAO.getCouponByCode(transaction2.getCouponCode());
					
					if(coupon!=null){
						
							if(coupon.getRedeemedBy()==null){
								transaction2.setCompletedRedemptions(coupon.getCompletedRedemptions());
								boolean isUpdated = transactionDAO.UpdateTransaction(transaction2);
								String redeemKey  = AuthenticationUtils.generateTokenForAuthentication();
								coupon.setRedeemedBy(transaction2.getUserEmail());
								coupon.setCouponCode(transaction2.getCouponCode());
								coupon.setRedeemKey(redeemKey);
								boolean isCreated = transactionDAO.createRedeemKey(coupon);
								    if(isCreated){
								    	modelMap.put("redeemKey", redeemKey);
								    }
								    modelMap.put("refferedUser", "false");
							}else{
								transaction2.setCompletedRedemptions(coupon.getCompletedRedemptions());
								boolean isUpdated = transactionDAO.UpdateRedeemedTrasaction(transaction2);
								modelMap.put("refferedUser", "true");
							}
					
					}
				    if(validCoupon.getAvailableRedemptions()-1 == validCoupon.getCompletedRedemptions()){
				    	boolean isUpdated = transactionDAO.updateCoupon(validCoupon.getCouponCode());
						Transaction transaction3 = transactionDAO.getTransactionDetailsByEmail(validCoupon.getRedeemedBy(),validCoupon.getEventId());
						long referedAmount = NumberUtils.toLong(validCoupon.getCouponValue())+NumberUtils.toLong(transaction3.getCouponValue());
						transaction3.setCouponValue(referedAmount+"");
						boolean updateRef = transactionDAO.updateTransactionByEmail(transaction3);
						CouponPayment cp =  new CouponPayment();
						System.out.println("eventid>>>>>>>>>>>>>>>>"+validCoupon.getEventId());
						cp.setEventId(validCoupon.getEventId()+"");
						cp.setCouponId(validCoupon.getCouponId()+"");
						cp.setEmailAddress(transaction3.getUserEmail());
						cp.setCurrencyCode("USD");
						cp.setAmount(validCoupon.getCouponValue());
						boolean isSuccess = sendSuperUserPayment(cp);
						System.out.println("IS-SUCCESS"+isSuccess);
						
				    }
					if(couponPayment.getMobile()!=null && couponPayment.getMobile().length()>4) {
						SMSUtil.sendSMS(couponPayment.getMobile(),transaction2.getCouponValue());
					}
					modelMap.put("action", "success");
					modelMap.put("message", "success");
				} else {
					modelMap.addAttribute("Error", resp.getErrors());
					Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
					transaction2.setStatus("ERROR");
					transactionDAO.UpdateTransaction(transaction2);
					log.debug(resp.getErrors().toString());
					//response.sendRedirect(this.getServletContext().getContextPath()+"/Error.jsp");
					modelMap.put("action", "error");
					modelMap.put("message", "Please check your Coupon Code");
				}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			modelMap.put("action", "already");
			modelMap.put("message", "Email is already Used for this coupon!");			
			}
		}
		}else{
			modelMap.put("action", "already");
			modelMap.put("message", "Coupon is Expired!");
			
		}
		
	}
	
	public boolean sendSuperUserPayment( CouponPayment couponPayment) {
		
		log.debug("Begining of sendMassPayment() ::::"+couponPayment.getAmount()+", "+couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO.getTransactionDetailsByEmail(couponPayment.getEmailAddress(),NumberUtils.toLong(couponPayment.getEventId()));
		boolean isSent = false;

		System.out.println("trans//////////"+transactionDetails.getEventId());
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
			
			//boolean isTransactionInit = transactionDAO.initTransaction(transaction);
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
