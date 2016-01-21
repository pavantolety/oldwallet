package com.oldwallet.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.util.paypal.Configuration;
import com.paypal.core.ClientCredentials;
import com.paypal.core.rest.APIContext;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.sdk.openidconnect.Session;
import com.paypal.svcs.services.AdaptiveAccountsService;
import com.paypal.svcs.types.aa.AccountIdentifierType;
import com.paypal.svcs.types.aa.GetVerifiedStatusRequest;
import com.paypal.svcs.types.aa.GetVerifiedStatusResponse;
import com.paypal.svcs.types.common.RequestEnvelope;
@Controller
public class TestClass {

	@RequestMapping(value="/test", method= RequestMethod.GET)
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

		
		
	
	}
}
