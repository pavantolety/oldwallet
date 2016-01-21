package com.oldwallet.model;

public class PaypalOAuthResponse {
	
	String scope;
	String code;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
