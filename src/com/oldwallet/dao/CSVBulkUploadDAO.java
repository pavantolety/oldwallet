package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.CouponData;

public interface CSVBulkUploadDAO {

	public boolean getCouponData(String couponCode);

	public boolean createCouponData(CouponData couponData);

	public List<CouponData> getCouponTrackingData();
}
