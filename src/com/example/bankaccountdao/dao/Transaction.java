package com.example.bankaccountdao.dao;

import java.util.ArrayList;

public class Transaction {
	private TransactionDAO transactionDAO;

	public void setTransactionDAO(TransactionDAO mockTransaction) {
		this.transactionDAO = mockTransaction;
	}

	public void doTransaction(String accountNumber, long time, long amount,
			String desc) {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setAccountNumber(accountNumber);
		transaction.setTime(time);
		transaction.setAmount(amount);
		transaction.setDesc(desc);
		transactionDAO.insert(transaction);
	}

	public ArrayList<TransactionDTO> getTransactions(String accountNumber) {
		return transactionDAO.getListOfTransactionDTO(accountNumber);
	}

	public ArrayList<TransactionDTO> getTransactions(String accountNumber,
			long begin, long end) {
		return transactionDAO
				.getListOfTransactionDTO(accountNumber, begin, end);
	}

	public ArrayList<TransactionDTO> getTransactions(String accountNumber, int n) {
		return transactionDAO.getListOfTransactionDTO(accountNumber, n);
	}
}