package com.example.bankaccountdao.dao;

import java.util.Calendar;

public class BankAccount {
	private BankAccountDAO bankAccountDAO;
	private Calendar cal;
	private Transaction trans;

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public BankAccountDAO getBankAccountDAO() {
		return bankAccountDAO;
	}

	public void setBankAccountDAO(BankAccountDAO bankAccountDAO) {
		this.bankAccountDAO = bankAccountDAO;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public BankAccountDTO openAccount(String accountNumber) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
		bankAccountDTO.setAccountNumber(accountNumber);
		bankAccountDTO.setTime(cal.getTimeInMillis());
		bankAccountDAO.insert(bankAccountDTO);
		return bankAccountDTO;
	}

	public void deposit(String accountNumber, int amount, String desc) {
		BankAccountDTO accountDTO = bankAccountDAO
				.getBankAccountDTO(accountNumber);
		accountDTO.setBalance(accountDTO.getBalance() + amount);
		accountDTO.setDesc(desc);
		bankAccountDAO.update(accountDTO);
		long time = cal.getTimeInMillis();
		trans.doTransaction(accountNumber, time, amount, desc);
	}

	public void withdraw(String accountNumber, int amount, String desc) {
		BankAccountDTO accountDTO = bankAccountDAO
				.getBankAccountDTO(accountNumber);
		accountDTO.setBalance(accountDTO.getBalance() - amount);
		accountDTO.setDesc(desc);
		bankAccountDAO.update(accountDTO);
		long time = cal.getTimeInMillis();
		trans.doTransaction(accountNumber, time, -amount, desc);
	}

	public BankAccountDTO getBankAccountDTO(String accountNumber) {
		return bankAccountDAO.getBankAccountDTO(accountNumber);
	}
}