package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponStatistics;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.UserToken;

public interface CouponDAO {

	public Coupon getCouponByCode(String couponCode);
	
	public Coupon getEncCouponByCode(String couponCode);
	
	public Coupon getEncBlockedCouponByCode(String couponCode);
	
	public FundAllocation  assignValueToCoupon();
	
	public List<Long> getAllCategories();
	
	public FundAllocation getFundByCateId(long id);
	
	public boolean updateFundAllocation(FundAllocation fundAllocation);
	
	public boolean createFundAllocation(FundAllocation fundAllocation);

	public boolean updateCoupon(String couponCode);

	public boolean updateCouponData(Coupon coupon);

	public boolean isCouponExists(String couponCode);
	
	public boolean createGeneratedCouponData(Coupon coupon);

	public FundAllocation getFundData();

	public UserToken getRedeemKey(String redeemKey);

	public CouponStatistics getTotalCouponCount();

	public CouponStatistics getRedeemedCount();

	public CouponStatistics getTotalCouponAmount();

	public CouponStatistics getReedmedAmount();

	public List<CouponStatistics> getCouponDataByReedeemStatus();
	
	public boolean blockCouponCode(Coupon coupon);

	public FundAllocation getFundAllocationData();

	public FundAllocation getFundAllocationDataByCode(String categoryCode);

	public boolean updateFundAllocationData(FundAllocation fundAllocationData);

	List<Coupon> getCouponData();

	public List<Coupon> getCouponDataByRedeemed();

}
