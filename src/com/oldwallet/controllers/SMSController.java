package com.oldwallet.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;

@Controller
public class SMSController {
	
	private static Logger log = Logger.getLogger(SMSController.class);
	
	@RequestMapping(value="/sendSMS/{mobile}", method = RequestMethod.GET)
	public String sendSMS(ModelMap modelMap, @PathVariable String mobile, String amount,HttpSession session) {
		
		 log.debug("Begining of sendSMS ::");

		String Account_Sid = "ACb2e73fe6a36429265bbd587a74ae9bb7";
		String Auth_Token = "5ee6ef76aeae2121ee06a3d6a90a330e";

		TwilioRestClient client = new TwilioRestClient(Account_Sid, Auth_Token);

		Account account = client.getAccount();

		MessageFactory messageFactory = account.getMessageFactory();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", mobile)); 
		params.add(new BasicNameValuePair("From", "855-851-3299")); 
		params.add(new BasicNameValuePair("Body","Your coupon amount "+amount+" is successfully credited to your Paypal account."));

		try {
			com.twilio.sdk.resource.instance.Message sms = messageFactory.create(params);
			log.debug("smsStatus :: "+sms.getStatus());
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}
		return "";
	}


}
