package com.oldwallet.dao;

import com.oldwallet.model.Coupon;

public interface CouponDAO {
	
	public Coupon getCouponByCode(String couponCode);
}
