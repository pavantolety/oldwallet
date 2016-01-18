package com.oldwallet.dao;

import com.oldwallet.model.SaveConfiguration;

public interface GenerateCouponDAO {
	
	public boolean saveConfiguration(SaveConfiguration saveConfiguration);
	
	public SaveConfiguration getDataById(long requestId);
}
