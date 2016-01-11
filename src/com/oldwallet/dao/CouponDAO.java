package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.UserToken;

public interface CouponDAO {

	public Coupon getCouponByCode(String couponCode);

	public boolean updateCoupon(String couponCode);
	
	public boolean isCouponExists(String couponCode);
	
	public List<Coupon> getCouponData();

	public UserToken getRedeemKey(String redeemKey);

	
}
