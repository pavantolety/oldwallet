package com.oldwallet.dao;

import java.util.List;

import com.oldwallet.model.MonthlyRedeemCouponsCount;
import com.oldwallet.model.Transaction;

public interface TransactionDAO {

	public boolean initTransaction(Transaction transaction);

	public boolean UpdateTransaction(Transaction transaction);

	public Transaction getTransactionDetailsById(String transId);
	
	public List<MonthlyRedeemCouponsCount> getRedeemedCouponsCountByMonth();
	
	public List<MonthlyRedeemCouponsCount> getCouponsCount();
}
