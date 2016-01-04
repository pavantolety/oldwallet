package com.oldwallet.model;

public class Coupon {
	
	private long couponId;
	
	private long eventId;
	
	private String couponCode;
	
	private String couponValue;
	
	private String couponHideLocation;
	
	private String redeemStatus;

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponHideLocation() {
		return couponHideLocation;
	}

	public void setCouponHideLocation(String couponHideLocation) {
		this.couponHideLocation = couponHideLocation;
	}

	public String getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(String redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
}
