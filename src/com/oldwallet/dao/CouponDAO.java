package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.Coupon;

public interface CouponDAO {

	public Coupon getCouponByCode(String couponCode);

	public boolean updateCoupon(String couponCode);
	
	public boolean isCouponExists(String couponCode);
	
	public List<Coupon> getCouponData();
}
