package com.oldwallet.config;

public interface SystemParams {

	public static final String BASEURL = "https://oldwallet.edvenswa.com/";

	//public static final String BASEURL = "http://localhost:8085/";

	public static final String TWILIO_ACCOUNT_ID = "ACb2e73fe6a36429265bbd587a74ae9bb7";
	public static final String TWILIO_AUTH_TOKEN = "5ee6ef76aeae2121ee06a3d6a90a330e";
	public static final String TWILIO_SMS_NUMBER = "855-851-3299";
	public static final String MASS_PAY_DEFAULT_CURRENCY_CODE = "USD";
	public static final String DEVICE_IP_FOR_PAYPAL = "127.0.0.1";
	public static final String SECRET_KEY = "OL2199E5PA601100";
	public static final String ENCRYP_ALGO = "AES";
	public static final String PAYPAL_LOCAL_REDIRECT = "http://localhost:9090/redeemed";
	public static final String PAYPAL_LIVE_REDIRECT = "https://oldwallet.edvenswa.com/redeemed";
}
