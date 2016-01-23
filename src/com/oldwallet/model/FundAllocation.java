package com.oldwallet.model;

public class FundAllocation {

	private long fundId;
	private String categoryCode;
	private String totalCouponCount;
	private String couponValue;
	private String redeemedCount;
	private String availableCount;
	private String couponCode;
	private double totalCouponValue;
	private double totalFund;
	
	public FundAllocation(){
		
		
	}
	public FundAllocation(long fundId,String categoryCode,String totalCouponCount,String couponValue,String redeemedCount,String availableCount){
		
		this.fundId =  fundId;
		this.categoryCode = categoryCode;
		this.totalCouponCount =  totalCouponCount;
		this.couponValue =  couponValue;
		this.redeemedCount =  redeemedCount;
		this.availableCount =  availableCount;
		
	}
	
	
	
	public double getTotalFund() {
		return totalFund;
	}
	public void setTotalFund(double totalFund) {
		this.totalFund = totalFund;
	}

	public double getTotalCouponValue() {
		return totalCouponValue;
	}
	public void setTotalCouponValue(double totalCouponValue) {
		this.totalCouponValue = totalCouponValue;
	}
	public long getFundId() {
		return fundId;
	}
	public void setFundId(long fundId) {
		this.fundId = fundId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getTotalCouponCount() {
		return totalCouponCount;
	}
	public void setTotalCouponCount(String totalCouponCount) {
		this.totalCouponCount = totalCouponCount;
	}
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public String getRedeemedCount() {
		return redeemedCount;
	}
	public void setRedeemedCount(String redeemedCount) {
		this.redeemedCount = redeemedCount;
	}
	public String getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(String availableCount) {
		this.availableCount = availableCount;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	
}
