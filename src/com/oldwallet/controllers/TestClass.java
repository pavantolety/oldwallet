package com.oldwallet.controllers;

import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.oldwallet.util.paypal.Configuration;
@Controller
public class TestClass {

/*	@RequestMapping(value="/test", method= RequestMethod.GET)
public void verifyEmailAddress(ModelMap modelMap, String emailAddress, HttpServletRequest request) {
		
	  	// GetVerifiedStatus Request
		RequestEnvelope requestEnvelope = new RequestEnvelope();
		requestEnvelope.setErrorLanguage("en_US");
		Map<String,String> configurationMap = Configuration.getAcctAndConfig();
		
		AdaptiveAccountsService service = new AdaptiveAccountsService(configurationMap);
		
		GetVerifiedStatusRequest req = new GetVerifiedStatusRequest(requestEnvelope, "NONE");
		
		req.setEmailAddress("buy@inspirave.com");
		req.setFirstName(request.getParameter("Syam"));
		req.setLastName(request.getParameter("Danda"));
		
		AccountIdentifierType accountIdentifier = new AccountIdentifierType();
		
		accountIdentifier.setEmailAddress("buy@inspirave.com");
		
		req.setAccountIdentifier(accountIdentifier);
		
		Map<String, String> configurationMap1 = new HashMap<String, String>();
	    configurationMap1.put("mode", "sandbox");

	    APIContext apiContext = new APIContext();
	    apiContext.setConfigurationMap(configurationMap1);

	    List<String> scopelist = new ArrayList<String>();
	    scopelist.add("openid");
	    scopelist.add("email");
	    String redirectURI = "http://localhost:9090";

	    ClientCredentials clientCredentials = new ClientCredentials();
	    clientCredentials.setClientID("IDASO1me3eFX_KUT7nkP1wWzHHhRab6xtZ0DJK3c7r11fQFFb-myrjtmbzj7D3v1-yYZVzF1Kt2nXN0tT7");

	    String redirectUrl = Session.getRedirectURL(redirectURI, scopelist, apiContext, clientCredentials);
	    
	    System.out.println(redirectUrl);
		
		GetVerifiedStatusResponse resp = null;
		try {
			resp = service.getVerifiedStatus(req);
		} catch (SSLConfigurationException | InvalidCredentialException
				| HttpErrorException | InvalidResponseDataException
				| ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (resp != null) {
			
			if (resp.getResponseEnvelope().getAck().toString().equalsIgnoreCase("SUCCESS")) {
				Map<Object, Object> map = new LinkedHashMap<Object, Object>();
				map.put("Ack", resp.getResponseEnvelope().getAck());
				map.put("Correlation ID", resp.getResponseEnvelope().getCorrelationId());
				map.put("TimeStamp", resp.getResponseEnvelope().getTimestamp());
				map.put("AccountStatus", resp.getAccountStatus());
				map.put("AccountID", resp.getUserInfo().getAccountId());
				map.put("Account Type", resp.getUserInfo().getAccountType());
				System.out.println(map);
				System.out.println("Ack"+resp.getResponseEnvelope().getAck());
				System.out.println("AccountStatus"+resp.getAccountStatus());
				System.out.println("Account Type"+resp.getUserInfo().getAccountType());
				
			} else {
				System.out.println("Error :: "+resp.getError());
			}
		}

		
		
	
	}*/

	@RequestMapping(value="/massPayTest", method= RequestMethod.GET)
	public void massPayTest() {
		System.out.println("Beginning Of massPayTest ::");
		MassPayReq req = new MassPayReq();

		List<MassPayRequestItemType> massPayItem = new ArrayList<MassPayRequestItemType>();

		BasicAmountType amount1 =  new BasicAmountType(CurrencyCodeType.fromValue("USD"), "10");
		
		MassPayRequestItemType item1 = null;
			item1 = new MassPayRequestItemType(amount1);
			item1.setReceiverEmail("syam@edvenswa.com");
			massPayItem.add(item1);
			
		if (!massPayItem.isEmpty()) {
			MassPayRequestType reqType = new MassPayRequestType(massPayItem);
			reqType.setReceiverType(ReceiverInfoCodeType.fromValue("EmailAddress"));
			req.setMassPayRequest(reqType);
			Map<String, String> configurationMap = Configuration.getAcctAndConfig();

			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

			try {
				System.out.println("Calling Mass Pay API ::");
				//System.setProperty("socketSecureFactory", "com.paypal.sdk.core.DefaultSSLFactory");
				
					    
					    MassPayResponseType resp = service.massPay(req);
				if (resp != null) {
					System.out.println("lastReq"+service.getLastRequest());
					System.out.println("lastResp"+service.getLastResponse());
					if ("SUCCESS".equalsIgnoreCase(resp.getAck().toString())) {
						Map<Object, Object> map = new LinkedHashMap<Object, Object>();
						map.put("Ack", resp.getAck());
						System.out.println("map"+map);
						System.out.println("Success :: " + resp.toString());
					} else {
						System.out.println("Error"+resp.getErrors());
						System.out.println(resp.getErrors().toString());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();				
			}
		} else {
			System.out.println("action ::"+" Error");
			System.out.println("message ::"+" Unable to process your request");
		}
			
		
	}
	
	
	
	public static void main(String args[]) {
		TestClass testObject = new TestClass();
		
		
        
		testObject.massPayTest();
	}

}
