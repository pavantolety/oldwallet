package com.oldwallet.model;

public class UserLogin {
	
	private String id;

	private String emailAddress;

	private String couponCode;
	
	private String amount;





	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
