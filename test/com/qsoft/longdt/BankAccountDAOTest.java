package com.qsoft.longdt;

import android.test.AndroidTestCase;

import com.example.bankaccountdao.dao.BankAccountDAO;
import com.example.bankaccountdao.dao.BankAccountDTO;

public class BankAccountDAOTest extends AndroidTestCase {

	private BankAccountDAO baDAO;
	private String accNumber;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		baDAO = new BankAccountDAO(getContext(), null);
		accNumber = "0123456789";
	}

	public void testOpenNewAccount() {
		BankAccountDTO bankAccount = createAccount(accNumber);
		boolean result = baDAO.insert(bankAccount);
		assertEquals(true, result);
		assertEquals(1, baDAO.getTotalRecord());
	}

	public void testGetAccountByAccountNumber() {
		BankAccountDTO bankAccount = createAccount(accNumber);
		baDAO.insert(bankAccount);
		BankAccountDTO baGot = baDAO.getBankAccountDTO(accNumber);
		assertTrue(null != baGot);
		assertTrue(bankAccount == baGot);
	}

	public void testBlockDuplicateAccountOpen() throws Exception {
		BankAccountDTO bankAccount = createAccount(accNumber);
		// insert 1 time
		baDAO.insert(bankAccount);

		bankAccount = createAccount(accNumber);
		// insert 2 times
		baDAO.insert(bankAccount);
		// Total records in database is only 1
		assertEquals(1, baDAO.getTotalRecord());
	}

	public void testUpdateBankAccount() {
		baDAO.insert(createAccount(accNumber));
		BankAccountDTO bankAccount = baDAO.getBankAccountDTO(accNumber);
		long amount = 500;
		long timeStamp = System.currentTimeMillis();
		bankAccount.setBalance(bankAccount.getBalance() + amount);
		bankAccount.setTime(timeStamp);
		boolean result = baDAO.update(bankAccount);
		bankAccount = baDAO.getBankAccountDTO(accNumber);
		assertEquals(1, result);
		assertEquals(amount, bankAccount.getBalance(), 0.01);
		assertEquals(timeStamp, bankAccount.getTime());
	}

	private BankAccountDTO createAccount(String accNumber) {
		BankAccountDTO bankAccount = new BankAccountDTO();
		bankAccount.setAccountNumber(accNumber);
		bankAccount.setTime(System.currentTimeMillis());
		bankAccount.setBalance(0);
		return bankAccount;
	}
}
