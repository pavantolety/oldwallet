package com.oldwallet.dao;

import com.oldwallet.model.UserSession;

public interface CouponBlockerDAO {
	
	public boolean insertCouponBlocker(UserSession session);
	
	public boolean updateCouponBlocker(UserSession userSession);
	
	public boolean updateCouponBlockerJob();

}
