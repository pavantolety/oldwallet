package com.oldwallet.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.oldwallet.config.SystemParams;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;

public class SMSUtil {
	
	private static final Logger LOGGER = Logger.getLogger(SMSUtil.class);
	
public static String sendSMS(String mobile, String amount) {
		
		LOGGER.debug("Begining of sendSMS ::");

		TwilioRestClient client = new TwilioRestClient(SystemParams.TWILIO_ACCOUNT_ID, SystemParams.TWILIO_AUTH_TOKEN);

		Account account = client.getAccount();

		MessageFactory messageFactory = account.getMessageFactory();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", mobile)); 
		params.add(new BasicNameValuePair("From", SystemParams.TWILIO_SMS_NUMBER)); 
		params.add(new BasicNameValuePair("Body","Your coupon amount $"+amount+" is successfully credited to your Paypal account."));

		try {
			com.twilio.sdk.resource.instance.Message sms = messageFactory.create(params);
			LOGGER.debug("smsStatus :: "+sms.getStatus());
		} catch (TwilioRestException e) {
			e.printStackTrace();
			
		}
		return "";
	}

}