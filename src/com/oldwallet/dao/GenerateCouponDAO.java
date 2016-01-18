package com.oldwallet.dao;

import com.oldwallet.model.SaveConfiguration;

public interface GenerateCouponDAO {
	
	public boolean saveConfiguration(SaveConfiguration saveConfiguration);
	
	public boolean storeCoupons(String couponCode);
}
