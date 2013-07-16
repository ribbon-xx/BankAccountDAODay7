package com.qsoft.longdt;

import java.util.ArrayList;

import android.test.AndroidTestCase;

import com.example.bankaccountdao.dao.TransactionDAO;
import com.example.bankaccountdao.dao.TransactionDTO;

public class TransactionDAOTest extends AndroidTestCase {

	private TransactionDAO transactionDAO;
	private String accNumber;
	private String description;
	private int amount;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		transactionDAO = new TransactionDAO(getContext(), null);
		accNumber = "0123456789";
		description = "Deposit";
		amount = 500;
	}

	public void testSaveNewTransaction() {
		long timestamp = System.currentTimeMillis();
		TransactionDTO transaction = createTransaction(accNumber, timestamp,
				amount, description);
		long result = transactionDAO.insert(transaction);
		assertEquals(1, result);
	}

	public void testGetTransactionsByAccountNumber() {
		Long timestamp = System.currentTimeMillis();
		TransactionDTO transaction = createTransaction(accNumber, timestamp,
				amount, description);
		transactionDAO.insert(transaction);
		ArrayList<TransactionDTO> list = transactionDAO
				.getListOfTransactionDTO(accNumber);
		assertTrue(list != null);
		assertEquals(1, list.size());
	}

	public void testGetTransactionsOccurredWithPeriodOfTime() {
		int amount = 500;
		long startTime = 1000L;
		long stopTime = 2000L;
		TransactionDTO transaction = createTransaction(accNumber, startTime,
				amount, description);
		transactionDAO.insert(transaction);

		description = "Withdraw";
		transaction = createTransaction(accNumber, stopTime, -amount,
				description);
		transactionDAO.insert(transaction);

		ArrayList<TransactionDTO> list = transactionDAO
				.getListOfTransactionDTO(accNumber, startTime, stopTime);
		assertTrue(list != null);
		assertEquals(2, list.size());
	}

	public void testGetTransactionsOccurredWithANumber() {
		for (int i = 0; i < 10; i++) {
			TransactionDTO transaction = createTransaction(accNumber,
					System.currentTimeMillis(), amount + i, description + i);
			transactionDAO.insert(transaction);
		}
		int numberRecord = 2;
		ArrayList<TransactionDTO> list = transactionDAO
				.getListOfTransactionDTO(accNumber, numberRecord);
		assertTrue(list != null);
		assertEquals(numberRecord, list.size());
	}

	private TransactionDTO createTransaction(String accountNumber,
			Long timestamp, int amount, String description) {
		TransactionDTO transaction = new TransactionDTO();
		transaction.setAccountNumber(accountNumber);
		transaction.setTime(timestamp);
		transaction.setAmount(amount);
		transaction.setDesc(description);
		return transaction;
	}
}
