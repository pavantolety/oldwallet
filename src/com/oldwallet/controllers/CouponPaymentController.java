package com.oldwallet.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.config.SystemParams;
import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponBlockerDAO;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.PaypalOAuthResponse;
import com.oldwallet.model.UserLogin;
import com.oldwallet.model.UserSession;
import com.oldwallet.model.UserToken;
import com.paypal.core.ClientCredentials;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.sdk.openidconnect.CreateFromAuthorizationCodeParameters;
import com.paypal.sdk.openidconnect.Session;
import com.paypal.sdk.openidconnect.Tokeninfo;
import com.paypal.sdk.openidconnect.Userinfo;
import com.paypal.sdk.openidconnect.UserinfoParameters;

@Controller
public class CouponPaymentController {

	private static final Logger LOGGER = Logger.getLogger(CouponPaymentController.class);
	private static final String STATUS = "status";
	private static final String COUPON = "coupon";
	private static final String ACTION = "action";
	private static final String MESSAGE = "message";
	private static final String NEW = "NEW";
	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String INVALID = "invalid";
	private static final String EXPIRED = "expired";
	private static final String SUCCESS = "success";
	private static final String INVALID_COUPON = "Invalid Coupon";	
	private static final String EXPIRED_COUPON = "Coupon Code Expired or Event Closed.!";
	private static final String VALID_COUPON = "You have entered a valid coupon.!";

	@Autowired
	CouponDAO couponDAO;

	@Autowired
	TransactionDAO transactionDAO;

    @Autowired
    CouponBlockerDAO couponBlockerDAO;
     
  @Scheduled(cron="0 0/5 * * * *")
	@RequestMapping(value = "updateCouponBlocker")
	public void updateCouponBlocker(){
		couponBlockerDAO.updateCouponBlockerJob();
	}
  
	@RequestMapping(value = "/saveCouponData", method = RequestMethod.POST)
	public void saveCouponData(ModelMap modelMap, Coupon coupon)throws ParseException {
		LOGGER.debug("Beginning Of Validating Coupon ::: " + coupon);

		if (coupon != null) {
			SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			coupon.setValidFrom(format2.format(format1.parse(coupon.getValidFrom())));
			coupon.setValidTo(format2.format(format1.parse(coupon.getValidTo())));
			boolean isUpdated = couponDAO.updateCouponData(coupon);
			if (isUpdated) {
				modelMap.put(STATUS, SUCCESS);

			} else {
				modelMap.put(STATUS, "failure");
			}
		}

	}

	@RequestMapping(value = "/validateCoupon", method = RequestMethod.POST)
	public void validateCoupon(ModelMap modelMap, Coupon coupon, HttpServletRequest request, HttpSession session) {
		LOGGER.debug("Beginning Of Validating Coupon ::: " + coupon);

		if (coupon != null && coupon.getCouponCode() != null) {
				LOGGER.debug("Coupon VALUE ENTERED :::");
				Coupon Ccoupon = couponDAO.getEncCouponByCode(coupon.getCouponCode());
				if (Ccoupon!=null) {
					LOGGER.debug("Coupon VALID :::");
					    modelMap.put(COUPON, Ccoupon);
						modelMap.put(ACTION, VALID);
						modelMap.put(MESSAGE, VALID_COUPON);
					} else {
						LOGGER.debug("Coupon INVALID :::");
						modelMap.put(ACTION, EXPIRED);
						modelMap.put(MESSAGE, EXPIRED_COUPON);
					}
			
			} else {
				LOGGER.debug("NULL COUPON :::");
				modelMap.put(ACTION, INVALID);
				modelMap.put(MESSAGE, EXPIRED_COUPON);
			}

		

	}

	@RequestMapping(value = "/redeemedKey", method = RequestMethod.GET)
	public String getCouponValidation(ModelMap modelMap, Coupon coupon) {
		LOGGER.debug("Beginnig of ValidCoupon Response ::");
		UserToken userToken = couponDAO.getRedeemKey(coupon.getRedeemKey());
		if (userToken != null) {
			Coupon validCoupon = couponDAO.getCouponByCode(userToken
					.getCouponCode());
			if (validCoupon != null) {
				if (validCoupon.getAvailableRedemptions() > 0
						&& NEW.equalsIgnoreCase(validCoupon.getRedeemStatus())) {
					// User entered a valid coupon.
					modelMap.put(COUPON, validCoupon);
					modelMap.put(ACTION, VALID);
					modelMap.put(MESSAGE, VALID_COUPON);
					return PageView.THANKYOU;
				} else {
					modelMap.put(ACTION, EXPIRED);
					modelMap.put(MESSAGE, EXPIRED_COUPON);

				}

				LOGGER.debug("Coupon is Valid ::");

				modelMap.put(COUPON, validCoupon);
				modelMap.put(ACTION, SUCCESS);
				modelMap.put(MESSAGE, "Valid Coupon");
			} else {
				// user entered expired coupon.
				modelMap.put(ACTION, ERROR);
				modelMap.put(MESSAGE, INVALID_COUPON);
			}
		} else {
			modelMap.put(ACTION, ERROR);
			modelMap.put(MESSAGE, INVALID_COUPON);
		}

		return PageView.THANKYOU;
	}

	@RequestMapping(value = "/valid", method = { RequestMethod.POST, RequestMethod.GET })
	public String validCouponResponse(ModelMap modelMap, Coupon coupon, HttpServletRequest request, HttpSession session) {
		LOGGER.debug("Beginnig of ValidCoupon Response ::");
		LOGGER.debug("End of ValidCoupon Response :>>>>>>:"+ request.getMethod());
		if ("GET" == request.getMethod()) {
			return "index";
		}
		String redirectUrl = null;
		String couponCode = coupon.getCouponCode();
		if (couponCode != null && couponCode != "" && couponCode.length() > 4) {
			Coupon Ccoupon = couponDAO.getEncCouponByCode(coupon.getCouponCode());
			if (Ccoupon!=null) {
				UserLogin userLogin = new UserLogin();
				Coupon coupon2 = asignValueToCoupon(Ccoupon.getCouponCode());
				if(coupon2!=null){
				userLogin.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				userLogin.setAmount(coupon2.getCouponValue());
				userLogin.setCouponCode(coupon2.getCouponCode());
				UserSession userSession = AuthenticationHelper.populateUserSession(userLogin);
				session.setAttribute("userSession", userSession);
				userSession.setAmount(coupon2.getCouponValue());
				boolean isBlocked = couponDAO.blockCouponCode(coupon2);
				LOGGER.info("isBlocked :: "+isBlocked);
				Map<String, String> configurationMap = new HashMap<String, String>();
				configurationMap.put("mode", "sandbox");

				APIContext apiContext = new APIContext();
				apiContext.setConfigurationMap(configurationMap);

				List<String> scopelist = new ArrayList<String>();
				scopelist.add("openid");
				scopelist.add("email");
				String redirectURI = SystemParams.PAYPAL_LOCAL_REDIRECT;

				ClientCredentials clientCredentials = new ClientCredentials();
				clientCredentials.setClientID(SystemParams.PAYPAL_LOCAL_ID);

				redirectUrl = Session.getRedirectURL(redirectURI, scopelist, apiContext, clientCredentials); 
					modelMap.put(COUPON, coupon2);
					modelMap.put(ACTION, VALID);
					modelMap.put(MESSAGE, VALID_COUPON);
					modelMap.put("redirectUrl", redirectUrl);
					return PageView.THANKYOU;
				}else{
					modelMap.put(ACTION, ERROR);
					modelMap.put(MESSAGE, INVALID_COUPON);
					return "index";
				}
				} else {
					modelMap.put(ACTION, ERROR);
					modelMap.put(MESSAGE, INVALID_COUPON);
					return "index";
				}
		} else {
			modelMap.put(ACTION, ERROR);
			modelMap.put(MESSAGE, INVALID_COUPON);
			return "index";
		}		
		
	}
     
	private Coupon asignValueToCoupon(String couponCode) {
		List<Long> cateIdList = couponDAO.getAllCategories();
		Coupon coupon =  new Coupon();
	    Collections.shuffle(cateIdList);	    
	    for(int i= 0;i<=cateIdList.size();i++){
	    	long id =  cateIdList.get(0);
	    	FundAllocation fa = couponDAO.getFundByCateId(id);
	    	if(fa!=null){	    		
	    		coupon.setCouponCode(couponCode);
	    		coupon.setCouponValue(fa.getCouponValue());
	    		fa.setCouponCode(couponCode);
	    		couponDAO.updateFundAllocation(fa);	    		
	    		break ;
	    	}else{
	    		continue;
	    	}
	    }
	   
		return coupon;
	}	

	@RequestMapping(value="/redeemed", method=RequestMethod.GET)
	public String redeemed(ModelMap modelMap, PaypalOAuthResponse paypalResponse, HttpSession session) throws Exception {
		String returnURL = "/redeemFailed";
		String emailAddress = null;
		TrustManager[] trustAllCerts = new TrustManager[] {
			    new X509TrustManager() {
			        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			            return null;
			        }
			        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			            //No need to implement. 
			        }
			        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			            //No need to implement. 
			        }
			    }
			};
			// Install the all-trusting trust manager
			try {
			    SSLContext sc = SSLContext.getInstance("SSL");
			    sc.init(null, trustAllCerts, new java.security.SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			    UserSession userSession = (UserSession) session.getAttribute("userSession");
				LOGGER.info("userSession :: "+userSession);
				LOGGER.info("COUPON CODE FROM SESSION :: "+userSession.getCouponCode());
				if(userSession != null) {
					LOGGER.info("UserSession is not null ::");
					Coupon validCoupon = couponDAO.getEncBlockedCouponByCode(userSession.getCouponCode());
					LOGGER.info("validCoupon ::"+validCoupon);
					if(validCoupon!= null) { 
						LOGGER.info("validCoupon is not null ::");
						Map<String, String> configurationMap = new HashMap<String, String>();
						configurationMap.put("mode", "sandbox");

						APIContext apiContext = new APIContext();
						apiContext.setConfigurationMap(configurationMap);

						CreateFromAuthorizationCodeParameters param = new CreateFromAuthorizationCodeParameters();
						param.setClientID(SystemParams.PAYPAL_LOCAL_ID);
						param.setClientSecret(SystemParams.PAYPAL_LOCAL_SECRET);
						param.setCode(paypalResponse.getCode());
						LOGGER.info("CODE ::: "+paypalResponse.getCode());
						Tokeninfo info = null;
						try {
							LOGGER.info("GOING TO GET ACCESS TOKEN FROM PAYPAL ::");
							info = Tokeninfo.createFromAuthorizationCode(apiContext, param);
							LOGGER.info("Going to authentication ::");
							if(info != null) {
								LOGGER.info("Authentication is success ::");
								String accessToken = info.getAccessToken();
								UserinfoParameters param2 = new UserinfoParameters();
								param2.setAccessToken(accessToken);
								LOGGER.info("Going to userInfo ::");
								Userinfo userInfo = Userinfo.getUserinfo(apiContext, param2);
								LOGGER.info("UserInfo ::"+userInfo);
								userSession.setEmailAddress(userInfo.getEmail());
								emailAddress  = userInfo.getEmail();
								LOGGER.info("EMAIL ADDRESS :: "+userInfo.getEmail());
								LOGGER.info("EMAIL ADDRESS :: "+emailAddress);
								}
						} catch (PayPalRESTException e) {
							e.printStackTrace();
							LOGGER.info("EXCEPTION WHILE SSL CONNECT ::");
						}						
						
					
					} else {
					    couponDAO.updateCoupon(userSession.getCouponCode());
						return "/redeemSuccess";
					}			
		
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return returnURL;
	}

}
