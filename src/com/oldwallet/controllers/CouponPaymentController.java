package com.oldwallet.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.oldwallet.config.SystemParams;
import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponPayment;
import com.oldwallet.model.Transaction;
import com.oldwallet.model.UserToken;
import com.oldwallet.util.AuthenticationUtils;
import com.oldwallet.util.ExceptionObjUtil;
import com.oldwallet.util.SMSUtil;
import com.oldwallet.util.paypal.Configuration;

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


	@RequestMapping(value = "/saveCouponData", method = RequestMethod.POST)
	public void saveCouponData(ModelMap modelMap, Coupon coupon)
			throws ParseException {
		LOGGER.debug("Beginning Of Validating Coupon ::: " + coupon);

		if (coupon != null) {
			SimpleDateFormat format1 = new SimpleDateFormat(
					"MM-dd-yyyy HH:mm:ss");
			SimpleDateFormat format2 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			coupon.setValidFrom(format2.format(format1.parse(coupon
					.getValidFrom())));
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
	public void validateCoupon(ModelMap modelMap, Coupon coupon) {
		LOGGER.debug("Beginning Of Validating Coupon ::: " + coupon);

		if (coupon != null && coupon.getCouponCode() != null) {
				LOGGER.debug("Coupon Exists :::");
				Coupon Ccoupon = couponDAO.getEncCouponByCode(coupon.getCouponCode());
				if (Ccoupon!=null) {
					    modelMap.put(COUPON, Ccoupon);
						modelMap.put(ACTION, VALID);
						modelMap.put(MESSAGE, VALID_COUPON);
					} else {

						modelMap.put(ACTION, EXPIRED);
						modelMap.put(MESSAGE, EXPIRED_COUPON);
					}
			
			} else {
				LOGGER.debug("Coupon INVALID :::");
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
	public String validCouponResponse(ModelMap modelMap, Coupon coupon, HttpServletRequest request) {
		LOGGER.debug("Beginnig of ValidCoupon Response ::");
		String couponCode = coupon.getCouponCode();
		if (couponCode != null && couponCode != "" && couponCode.length() > 4) {
			Coupon Ccoupon = couponDAO.getEncCouponByCode(coupon.getCouponCode());
			if (Ccoupon!=null) {
					modelMap.put(COUPON, Ccoupon);
					modelMap.put(ACTION, VALID);
					modelMap.put(MESSAGE, VALID_COUPON);
					return PageView.THANKYOU;
				} else {
					modelMap.put(ACTION, EXPIRED);
					modelMap.put(MESSAGE, EXPIRED_COUPON);
				}
		} else {
			modelMap.put(ACTION, ERROR);
			modelMap.put(MESSAGE, INVALID_COUPON);
		}
		LOGGER.debug("End of ValidCoupon Response :>>>>>>:"+ request.getMethod());
		if ("GET" == request.getMethod()) {
			return "index";
		}
		return PageView.THANKYOU;
	}

	@RequestMapping(value = "/getCouponAmount", method = RequestMethod.POST)
	public void sendMassPayment(ModelMap modelMap, CouponPayment couponPayment) {

		LOGGER.debug("Begining of sendMassPayment() ::::"
				+ couponPayment.getAmount() + ", "
				+ couponPayment.getEmailAddress());
		Transaction transactionDetails = transactionDAO
				.getTransactionDetailsByEmail(couponPayment.getEmailAddress(),
						NumberUtils.toLong(couponPayment.getEventId()));

		Coupon validCoupon = couponDAO.getCouponByCode(couponPayment
				.getCouponCode());

		if (validCoupon != null) {
			if (validCoupon.getCompletedRedemptions() < validCoupon
					.getAvailableRedemptions()
					&& validCoupon.getRedeemStatus().equalsIgnoreCase(NEW)) {
				if (transactionDetails == null) {

					MassPayReq req = new MassPayReq();

					List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

					BasicAmountType amount = new BasicAmountType(
							CurrencyCodeType.fromValue(couponPayment
									.getCurrencyCode()),
							couponPayment.getAmount());

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

	}

}
