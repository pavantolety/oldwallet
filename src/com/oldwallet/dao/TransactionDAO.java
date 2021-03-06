package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.Coupon;
import com.oldwallet.model.MonthlyRedeemCouponsCount;
import com.oldwallet.model.Transaction;
import com.oldwallet.model.MonthlyCouponsCount;

public interface TransactionDAO {

	public boolean initTransaction(Transaction transaction);

	public boolean updateTransaction(Transaction transaction);

	public boolean updateCoupon(String couponCode);

	public boolean updateRedeemedTrasaction(Transaction transaction);

	public Transaction getTransactionDetailsById(String transId);

	public Transaction getTransactionDetailsByEmail(String email, long eventId);

	public List<MonthlyRedeemCouponsCount> getRedeemedCouponsCountByMonth();

	public List<MonthlyCouponsCount> getMonthlyCouponsCount();

	public List<MonthlyCouponsCount> getExpiredMonthlyCouponsCount();

	public boolean updateTransactionByEmail(Transaction transaction);

	public List<Transaction> getRedeemedCouponData();

	public boolean createRedeemKey(Coupon coupon);
}
