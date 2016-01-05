package com.oldwallet.dao;

import com.oldwallet.model.Transaction;

public interface TransactionDAO {

	public boolean initTransaction(Transaction transaction);

	public boolean UpdateTransaction(Transaction transaction);

	public Transaction getTransactionDetailsById(String transId);
}