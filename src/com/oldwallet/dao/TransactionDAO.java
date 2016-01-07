package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.MonthlyRedeemCouponsCount;
import com.oldwallet.model.Transaction;
import com.oldwallet.model.MonthlyCouponsCount;

public interface TransactionDAO {

	public boolean initTransaction(Transaction transaction);

	public boolean UpdateTransaction(Transaction transaction);

	public Transaction getTransactionDetailsById(String transId);
	
	public List<MonthlyRedeemCouponsCount> getRedeemedCouponsCountByMonth();
	
	public List<MonthlyRedeemCouponsCount> getCouponsCount();
	
	public List<MonthlyCouponsCount> getMonthlyCouponsCount();
	
	public List<MonthlyCouponsCount> getExpiredMonthlyCouponsCount();
}
