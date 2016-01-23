package com.oldwallet.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

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
import com.oldwallet.dao.CouponBlockerDAO;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponPayment;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.PaypalOAuthResponse;
import com.oldwallet.model.Transaction;
import com.oldwallet.model.UserLogin;
import com.oldwallet.model.UserSession;
import com.oldwallet.model.UserToken;
import com.oldwallet.util.AuthenticationUtils;
import com.oldwallet.util.ExceptionObjUtil;
import com.oldwallet.util.SMSUtil;
import com.oldwallet.util.paypal.Configuration;
import com.paypal.core.ClientCredentials;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
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

	@RequestMapping(value = "/getCouponAmount", method = RequestMethod.POST)
	public void sendMassPayment(ModelMap modelMap, CouponPayment couponPayment) {

		LOGGER.debug("Begining of sendMassPayment() ::::"+ couponPayment.getAmount() + ", "+ couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO.getTransactionDetailsByEmail(couponPayment.getEmailAddress(),NumberUtils.toLong(couponPayment.getEventId()));

		Coupon validCoupon = couponDAO.getCouponByCode(couponPayment.getCouponCode());
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
		if (validCoupon != null) {
			if (validCoupon.getCompletedRedemptions() < validCoupon
					.getAvailableRedemptions()
					&& validCoupon.getRedeemStatus().equalsIgnoreCase(NEW)) {
				if (transactionDetails == null) {

					MassPayReq req = new MassPayReq();

					List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

					BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue(couponPayment.getCurrencyCode()),couponPayment.getAmount());

					MassPayRequestItemType item = new MassPayRequestItemType(
							amount);

					item.setReceiverEmail(couponPayment.getEmailAddress());
					massPayItem.add(item);

					MassPayRequestType reqType = new MassPayRequestType(
							massPayItem);
					reqType.setReceiverType(ReceiverInfoCodeType
							.fromValue("EmailAddress"));
					req.setMassPayRequest(reqType);
					Map<String, String> configurationMap = Configuration
							.getAcctAndConfig();
					PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(
							configurationMap);

					try {
						String transactionCode = UUID.randomUUID().toString()
								.replaceAll("-", "");
						LOGGER.debug("EventId ::: "
								+ couponPayment.getEventId());
						LOGGER.debug("CouponId ::: "
								+ couponPayment.getCouponId());
						LOGGER.debug("CouponCode ::: "
								+ couponPayment.getCouponCode());
						LOGGER.debug("Amount ::: " + couponPayment.getAmount());
						LOGGER.debug("Tran ::: " + transactionCode);

						Transaction transaction = new Transaction();
						transaction
								.setCouponCode(couponPayment.getCouponCode());
						transaction.setCouponId(couponPayment.getCouponId());
						transaction.setCouponValue(couponPayment.getAmount());
						transaction.setEventId(couponPayment.getEventId());
						transaction.setTransactionCode(transactionCode);
						transaction.setUserEmail(couponPayment
								.getEmailAddress());
						transaction.setUserMobile(couponPayment.getMobile());

						transactionDAO.initTransaction(transaction);
						MassPayResponseType resp = service.massPay(req);
						if (resp != null) {
							modelMap.addAttribute("lastReq",
									service.getLastRequest());
							modelMap.addAttribute("lastResp",
									service.getLastResponse());
							if ("SUCCESS".equalsIgnoreCase(resp.getAck()
									.toString())) {
								Map<Object, Object> map = new LinkedHashMap<Object, Object>();
								map.put("Ack", resp.getAck());
								modelMap.addAttribute("map", map);
								LOGGER.debug("Response Success :: "
										+ resp.toString());
								Transaction transaction2 = transactionDAO
										.getTransactionDetailsById(transactionCode);
								transaction2.setStatus("COMPLETE");
								LOGGER.debug("email is "
										+ transaction2.getUserEmail());
								transaction2.setLatitude(couponPayment
										.getLatitude());
								transaction2.setLongitude(couponPayment
										.getLongitude());

								Coupon coupon = couponDAO
										.getCouponByCode(transaction2
												.getCouponCode());
								LOGGER.debug("TRANS COUPON CODE >>>>>>>>>>>>>>>>>>>>"
										+ transaction2.getCouponCode());
								if (coupon != null) {
									LOGGER.debug("email address >>>>>>>>>>>>>>>>>>"
											+ coupon.getRedeemedBy());
									LOGGER.debug("email address size  >>>>>>>>>>>>>>>>>>"
											+ coupon.getRedeemedBy().length());
									if (coupon.getRedeemedBy().isEmpty()) {
										transaction2
												.setCompletedRedemptions(coupon
														.getCompletedRedemptions());
										transactionDAO
												.updateTransaction(transaction2);
										String redeemKey = AuthenticationUtils
												.generateTokenForAuthentication();
										LOGGER.debug("email >?????????????????????"
												+ transaction2.getUserEmail());
										coupon.setRedeemedBy(transaction2
												.getUserEmail());
										coupon.setCouponCode(transaction2
												.getCouponCode());
										coupon.setRedeemKey(redeemKey);
										boolean isCreated = transactionDAO
												.createRedeemKey(coupon);
										if (isCreated) {
											modelMap.put("redeemKey", redeemKey);
										}
										modelMap.put("refferedUser", "false");
									} else {
										transaction2
												.setCompletedRedemptions(coupon
														.getCompletedRedemptions());
										transactionDAO
												.updateRedeemedTrasaction(transaction2);
										modelMap.put("refferedUser", "true");
									}

								}
								if (validCoupon.getAvailableRedemptions() - 1 == validCoupon
										.getCompletedRedemptions()) {
									transactionDAO.updateCoupon(validCoupon
											.getCouponCode());
									Transaction transaction3 = transactionDAO
											.getTransactionDetailsByEmail(
													validCoupon.getRedeemedBy(),
													validCoupon.getEventId());
									long referedAmount = NumberUtils
											.toLong(validCoupon
													.getCouponValue())
											+ NumberUtils.toLong(transaction3
													.getCouponValue());
									transaction3.setCouponValue(referedAmount
											+ "");
									transactionDAO
											.updateTransactionByEmail(transaction3);
									CouponPayment cp = new CouponPayment();
									LOGGER.debug("eventid>>>>>>>>>>>>>>>>"
											+ validCoupon.getEventId());
									cp.setEventId(validCoupon.getEventId() + "");
									cp.setCouponId(validCoupon.getCouponId()
											+ "");
									cp.setEmailAddress(transaction3
											.getUserEmail());
									cp.setCurrencyCode(SystemParams.MASS_PAY_DEFAULT_CURRENCY_CODE);
									cp.setAmount(validCoupon.getCouponValue());

								}
								if (couponPayment.getMobile() != null
										&& couponPayment.getMobile().length() > 4) {
									SMSUtil.sendSMS(couponPayment.getMobile(),
											transaction2.getCouponValue());
								}
								modelMap.put(ACTION, SUCCESS);
								modelMap.put(MESSAGE, SUCCESS);
							} else {
								modelMap.addAttribute("Error", resp.getErrors());
								Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
								transaction2.setStatus("ERROR");
								transactionDAO.updateTransaction(transaction2);
								LOGGER.debug(resp.getErrors().toString());
								modelMap.put(ACTION, ERROR);
								modelMap.put(MESSAGE, "Please check your Coupon Code");
							}
						}

					} catch (Exception e) {
						LOGGER.info(e);
						ExceptionObjUtil.saveException("MassPay Exception",
								e.getMessage(), "CouponPaymentController.java",
								"sendMassPayment");
					}
				} else {
					modelMap.put(ACTION, "already");
					modelMap.put(MESSAGE, "Email is already Used for this coupon!");
				}
			}
		} else {
			modelMap.put(ACTION, "already");
			modelMap.put(MESSAGE, "Coupon is Expired!");

		}
			}catch (Exception e) {
		    System.out.println(e);
		}

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
				/*if(userSession != null) {*/
					LOGGER.info("UserSession is not null ::");
					Coupon validCoupon = couponDAO.getEncBlockedCouponByCode(userSession.getCouponCode());
					LOGGER.info("validCoupon ::"+validCoupon);
					if(validCoupon!= null) { //TODO Has to check this -- this is just a tweak for the demo. 
						LOGGER.info("validCoupon is not null ::");
						Map<String, String> configurationMap = new HashMap<String, String>();
						configurationMap.put("mode", "sandbox");

						APIContext apiContext = new APIContext();
						apiContext.setConfigurationMap(configurationMap);

						CreateFromAuthorizationCodeParameters param = new CreateFromAuthorizationCodeParameters();
						param.setClientID(SystemParams.PAYPAL_LIVE_ID);
						param.setClientSecret(SystemParams.PAYPAL_LIVE_SECRET);
						param.setCode(paypalResponse.getCode());
						LOGGER.info("CODE ::: "+paypalResponse.getCode());
						
						/* TEST CODE FOR SENDING MASS PAY*/
						MassPayReq req = new MassPayReq();
						LOGGER.info("GOING TO MASS PAY ::");
						List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

						BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue("USD"),userSession.getAmount());
						
						MassPayRequestItemType item = new MassPayRequestItemType(amount);
						MassPayRequestItemType item2 = new MassPayRequestItemType(amount);
						MassPayRequestItemType item3 = new MassPayRequestItemType(amount);
						MassPayRequestItemType item4 = new MassPayRequestItemType(amount);

						item.setReceiverEmail("syam@edvenswa.com");
						item2.setReceiverEmail("lalithp@gmail.com");
						item3.setReceiverEmail("malik@gmail.com");
						item4.setReceiverEmail("kalyan@gmail.com");
						
						massPayItem.add(item);
						massPayItem.add(item2);
						massPayItem.add(item3);
						massPayItem.add(item4);

						MassPayRequestType reqType = new MassPayRequestType(massPayItem);
						reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
						req.setMassPayRequest(reqType);
						LOGGER.info("GOING TO MASS PAY REQUEST::"+req);
						PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
						
						MassPayResponseType resp = null;
						try {
							LOGGER.info("GOING TO MASS PAY RESPONSE ::");
							resp = service.massPay(req);
							
							LOGGER.info("RESPONSE FROM MASSPAY :: "+resp);
							if (resp != null) {
								modelMap.addAttribute("lastReq",service.getLastRequest());
								modelMap.addAttribute("lastResp",service.getLastResponse());
								
								if ("SUCCESS".equalsIgnoreCase(resp.getAck().toString())) {
									Map<Object, Object> map = new LinkedHashMap<Object, Object>();
									map.put("Ack", resp.getAck());
									modelMap.addAttribute("map", map);
									LOGGER.debug("Response Success :: "+ resp.toString());
									
									returnURL = "/redeemSuccess";
									
						}
					}
							
							
						} catch (SSLConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidCredentialException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (HttpErrorException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						} catch (InvalidResponseDataException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						} catch (ClientActionRequiredException e5) {
							// TODO Auto-generated catch block
							e5.printStackTrace();
						} catch (MissingCredentialException e6) {
							// TODO Auto-generated catch block
							e6.printStackTrace();
						} catch (OAuthException e7) {
							// TODO Auto-generated catch block
							e7.printStackTrace();
						} catch (IOException e11) {
							// TODO Auto-generated catch block
							e11.printStackTrace();
						} catch (InterruptedException e8) {
							// TODO Auto-generated catch block
							e8.printStackTrace();
						} catch (ParserConfigurationException e9) {
							// TODO Auto-generated catch block
							e9.printStackTrace();
						} catch (SAXException e10) {
							// TODO Auto-generated catch block
							e10.printStackTrace();
						}

						Tokeninfo info = null;
						try {
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
							// TODO Auto-generated catch block
							e.printStackTrace();
							/*MassPayReq req = new MassPayReq();
							LOGGER.info("GOING TO MASS PAY ::");
							List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

							BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue("USD"),userSession.getAmount());
							
							MassPayRequestItemType item = new MassPayRequestItemType(amount);
							MassPayRequestItemType item2 = new MassPayRequestItemType(amount);
							MassPayRequestItemType item3 = new MassPayRequestItemType(amount);
							MassPayRequestItemType item4 = new MassPayRequestItemType(amount);

							item.setReceiverEmail("syam@edvenswa.com");
							item2.setReceiverEmail("lalithp@gmail.com");
							item3.setReceiverEmail("malik@gmail.com");
							item4.setReceiverEmail("kalyan@gmail.com");
							
							massPayItem.add(item);
							massPayItem.add(item2);
							massPayItem.add(item3);
							massPayItem.add(item4);

							MassPayRequestType reqType = new MassPayRequestType(massPayItem);
							reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
							req.setMassPayRequest(reqType);
							LOGGER.info("GOING TO MASS PAY REQUEST::"+req);
							PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
							
							MassPayResponseType resp = null;
							try {
								LOGGER.info("GOING TO MASS PAY RESPONSE ::");
								resp = service.massPay(req); 
								
								LOGGER.info("RESPONSE FROM MASSPAY :: "+resp);
								if (resp != null) {
									modelMap.addAttribute("lastReq",service.getLastRequest());
									modelMap.addAttribute("lastResp",service.getLastResponse());
									
									if ("SUCCESS".equalsIgnoreCase(resp.getAck().toString())) {
										Map<Object, Object> map = new LinkedHashMap<Object, Object>();
										map.put("Ack", resp.getAck());
										modelMap.addAttribute("map", map);
										LOGGER.debug("Response Success :: "+ resp.toString());
										
										returnURL = "/redeemSuccess";
										
							}
						}
								
								
							} catch (SSLConfigurationException e1) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvalidCredentialException e2) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (HttpErrorException e3) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvalidResponseDataException e4) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientActionRequiredException e5) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MissingCredentialException e6) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (OAuthException e7) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e11) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e8) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParserConfigurationException e9) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SAXException e10) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
							
							couponDAO.updateCoupon(userSession.getCouponCode());
							return "/redeemSuccess";
						}	
						
						/*MassPayReq req = new MassPayReq();

						List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

						BasicAmountType amount = new BasicAmountType(CurrencyCodeType.fromValue("USD"),userSession.getAmount());
						
						MassPayRequestItemType item = new MassPayRequestItemType(amount);
						MassPayRequestItemType item2 = new MassPayRequestItemType(amount);
						MassPayRequestItemType item3 = new MassPayRequestItemType(amount);
						MassPayRequestItemType item4 = new MassPayRequestItemType(amount);

						item.setReceiverEmail("syam@edvenswa.com");
						item2.setReceiverEmail("lalithp@gmail.com");
						item3.setReceiverEmail("malik@gmail.com");
						item4.setReceiverEmail("kalyan@gmail.com");
						
						massPayItem.add(item);
						massPayItem.add(item2);
						massPayItem.add(item3);
						massPayItem.add(item4);

						MassPayRequestType reqType = new MassPayRequestType(massPayItem);*/
						reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
						req.setMassPayRequest(reqType);
						
						//PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

						String transactionCode = UUID.randomUUID().toString();
						Transaction transaction = new Transaction();
						
						transaction.setCouponCode(userSession.getCouponCode());
						transaction.setCouponId(validCoupon.getCouponId()+"");
						transaction.setCouponValue(userSession.getAmount());
						transaction.setEventId(validCoupon.getEventId()+"");
						transaction.setTransactionCode(transactionCode);
						transaction.setUserEmail(emailAddress);
						
						transactionDAO.initTransaction(transaction);
						
						//MassPayResponseType resp = null;
						try {
							resp = service.massPay(req);
							
						} catch (SSLConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidCredentialException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (HttpErrorException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidResponseDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClientActionRequiredException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MissingCredentialException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OAuthException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						LOGGER.info("RESPONSE FROM MASSPAY :: "+resp);
						if (resp != null) {
							modelMap.addAttribute("lastReq",service.getLastRequest());
							modelMap.addAttribute("lastResp",service.getLastResponse());
							
							if ("SUCCESS".equalsIgnoreCase(resp.getAck().toString())) {
								Map<Object, Object> map = new LinkedHashMap<Object, Object>();
								map.put("Ack", resp.getAck());
								modelMap.addAttribute("map", map);
								LOGGER.debug("Response Success :: "+ resp.toString());
								Transaction transaction2 = transactionDAO.getTransactionDetailsById(transactionCode);
								transaction2.setStatus("COMPLETE");
								LOGGER.debug("email is "+ transaction2.getUserEmail());
								returnURL = "/redeemSuccess";
								Coupon coupon = couponDAO.getCouponByCode(transaction2.getCouponCode());
								LOGGER.debug("TRANS COUPON CODE >>>>>>>>>>>>>>>>>>>>"+ transaction2.getCouponCode());
								if (coupon != null) {
									LOGGER.debug("email address >>>>>>>>>>>>>>>>>>"+ coupon.getRedeemedBy());
									LOGGER.debug("email address size  >>>>>>>>>>>>>>>>>>"+ coupon.getRedeemedBy().length());
									if (coupon.getRedeemedBy().isEmpty()) {
										transaction2.setCompletedRedemptions(coupon.getCompletedRedemptions());
										transactionDAO.updateTransaction(transaction2);
										String redeemKey = AuthenticationUtils.generateTokenForAuthentication();
										LOGGER.debug("email >?????????????????????"+ transaction2.getUserEmail());
										coupon.setRedeemedBy(transaction2.getUserEmail());
										coupon.setCouponCode(transaction2.getCouponCode());
										coupon.setRedeemKey(redeemKey);
										boolean isCreated = transactionDAO.createRedeemKey(coupon);
										if (isCreated) {
											modelMap.put("redeemKey", redeemKey);
										}
										modelMap.put("refferedUser", "false");
									} else {
										transaction2.setCompletedRedemptions(coupon.getCompletedRedemptions());
										transactionDAO.updateRedeemedTrasaction(transaction2);
										modelMap.put("refferedUser", "true");
									}
								}
					}
				}
					} else {
					    couponDAO.updateCoupon(userSession.getCouponCode());
						return "/redeemSuccess";
					}
			} catch (Exception e) {
			    System.out.println(e);
			}
		  
		
		/*}*/
		return returnURL;
	}
			

}
