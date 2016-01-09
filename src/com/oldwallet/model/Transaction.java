package com.oldwallet.model;

public class Transaction {

	private String id;
	private String couponCode;
	private String couponId;
	private String couponValue;
	private String eventId;
	private String couponRetreiveLocation;
	private String couponRedeemTime;
	private String transactionCode;
	private String transactonCreaton;
	private String transactionUpdate;
	private String userEmail;
	private String userMobile;
	private String status;
    private String redeemedLocation;
    private String redeemedLocationCode;
    private int availableRedemptions;
    
    
	public int getAvailableRedemptions() {
		return availableRedemptions;
	}

	public void setAvailableRedemptions(int availableRedemptions) {
		this.availableRedemptions = availableRedemptions;
	}

	public String getRedeemedLocation() {
		return redeemedLocation;
	}

	public void setRedeemedLocation(String redeemedLocation) {
		this.redeemedLocation = redeemedLocation;
	}

	public String getRedeemedLocationCode() {
		return redeemedLocationCode;
	}

	public void setRedeemedLocationCode(String redeemedLocationCode) {
		this.redeemedLocationCode = redeemedLocationCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getCouponRetreiveLocation() {
		return couponRetreiveLocation;
	}

	public void setCouponRetreiveLocation(String couponRetreiveLocation) {
		this.couponRetreiveLocation = couponRetreiveLocation;
	}

	public String getCouponRedeemTime() {
		return couponRedeemTime;
	}

	public void setCouponRedeemTime(String couponRedeemTime) {
		this.couponRedeemTime = couponRedeemTime;
	}

	public String getTransactonCreaton() {
		return transactonCreaton;
	}

	public void setTransactonCreaton(String transactonCreaton) {
		this.transactonCreaton = transactonCreaton;
	}

	public String getTransactionUpdate() {
		return transactionUpdate;
	}

	public void setTransactionUpdate(String transactionUpdate) {
		this.transactionUpdate = transactionUpdate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
}
