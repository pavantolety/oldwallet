package com.oldwallet.model;

public class Coupon {
	
	private long couponId;
	
	private long eventId;
	
	private String couponCode;
	
	private String couponValue;
	
	private String couponHideLocation;
	
	private String redeemStatus;
	
	private String redeemedBy;
	
	private int availableRedemptions;
	
	private int completedRedemptions;
	
	private String validFrom;
	
	private String validTo;
	
	private String redeemedDate;
	
	 private String location;
	 
	 private String validityPeriod;	

	public int getCompletedRedemptions() {
		return completedRedemptions;
	}

	public void setCompletedRedemptions(int completedRedemptions) {
		this.completedRedemptions = completedRedemptions;
	}

	public String getRedeemedBy() {
		return redeemedBy;
	}

	public void setRedeemedBy(String redeemedBy) {
		this.redeemedBy = redeemedBy;
	}

	public int getAvailableRedemptions() {
		return availableRedemptions;
	}

	public void setAvailableRedemptions(int availableRedemptions) {
		this.availableRedemptions = availableRedemptions;
	}

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

	public String getRedeemedDate() {
		return redeemedDate;
	}

	public void setRedeemedDate(String redeemedDate) {
		this.redeemedDate = redeemedDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
}
