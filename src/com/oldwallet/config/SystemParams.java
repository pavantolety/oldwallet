package com.oldwallet.config;

public interface SystemParams {

	//public static final String BASEURL = "https://oldwallet.edvenswa.com/";
	public static final String BASEURL = "http://localhost:9090/";
	//public static final String BASEURL = "https://paypal-superbowl.com/";

    public static final String ADMIN_EMAIL="uss@edvenswa.com";
    public static final String ADMIN_PASSWORD= "@Superbowl16";

	public static final String TWILIO_ACCOUNT_ID = "ACb2e73fe6a36429265bbd587a74ae9bb7";
	public static final String TWILIO_AUTH_TOKEN = "5ee6ef76aeae2121ee06a3d6a90a330e";
	public static final String TWILIO_SMS_NUMBER = "855-851-3299";
	public static final String MASS_PAY_DEFAULT_CURRENCY_CODE = "USD";
	public static final String DEVICE_IP_FOR_PAYPAL = "127.0.0.1";
	public static final String SECRET_KEY = "OL2199E5PA601100";
	public static final String ENCRYP_ALGO = "AES";
	
	public static final String PAYPAL_LOCAL_REDIRECT = "http://localhost:9090/redeemed";
	public static final String PAYPAL_LOCAL_ID = "AXixS98I-lpuZ7XmAewtS8bFX0tTnr02H1gXo20t2px7D0dBkf_2H9degiORoWCQjHvrSZGhNqP1PU0f";
	public static final String PAYPAL_LOCAL_SECRET = "EG-CBZ7wVgAhONAM6bhv7gP1_d2x1BVW5pYz9-Shfm2J2BFvOVa7FFyr0v804qZyl_BopE4bhq9kTzyN";
	
	public static final String PAYPAL_LIVE_REDIRECT = "https://oldwallet.edvenswa.com/redeemed";
	public static final String PAYPAL_LIVE_ID = "ASO1me3eFX_KUT7nkP1wWzHHhRab6xtZ0DJK3c7r11fQFFb-myrjtmbzj7D3v1-yYZVzF1Kt2nXN0tT7";
	public static final String PAYPAL_LIVE_SECRET = "ELLrRWufa7Jt5n-QIeJiijdMTUs5Qxq4ZPycUbnKsJGqmxQFeD5GOJNR5QY2GVC5EIe_3tuQo-Qitc-Y";
	
	public static final String PAYPAL_PROD_REDIRECT = "https://paypal-superbowl.com/redeemed";
	public static final String PAYPAL_PROD_SECRET = "EEF80rs_lCmkuVN6Yc0FgHud-xvSA_Dl7QGCrvVCMVe2xaZPt-s5yMrz632ejSxPkmPfdFyqgAq2nrGG";
	public static final String PAYPAL_PROD_ID ="ARDctmh5TjxewjkQNECRVCsGoAOLLGlq8kZ7AA-qn1_HG9VIB7Mw5Q8SEjeZxTL_r0KR7PDrpaOPwHra";
}
