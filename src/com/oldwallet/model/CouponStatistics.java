package com.oldwallet.model;

public class CouponStatistics {

	private long totalCouponsCount;
	private double totalCouponAmount;
	private double totalRedeemedAmount;
	private long redeemedCouponCount;
	private String redeemStatus;

	public String getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(String redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public long getTotalCouponsCount() {
		return totalCouponsCount;
	}

	public void setTotalCouponsCount(long totalCouponsCount) {
		this.totalCouponsCount = totalCouponsCount;
	}

	public double getTotalCouponAmount() {
		return totalCouponAmount;
	}

	public void setTotalCouponAmount(double totalCouponAmount) {
		this.totalCouponAmount = totalCouponAmount;
	}

	public double getTotalRedeemedAmount() {
		return totalRedeemedAmount;
	}

	public void setTotalRedeemedAmount(double totalRedeemedAmount) {
		this.totalRedeemedAmount = totalRedeemedAmount;
	}

	public long getRedeemedCouponCount() {
		return redeemedCouponCount;
	}

	public void setRedeemedCouponCount(long redeemedCouponCount) {
		this.redeemedCouponCount = redeemedCouponCount;
	}

}
