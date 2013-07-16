package com.example.bankaccountdao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BankAccountDAO {
	private SQLiteDatabase db;

	public BankAccountDAO(Context context, String databaseName) {
		DBHelper openHelper = new DBHelper(context, databaseName);
		this.db = openHelper.getWritableDatabase();
	}

	public boolean insert(BankAccountDTO bankAccountDTO) {
		long result = 0;
		BankAccountDTO bankAccount = getBankAccountDTO(bankAccountDTO
				.getAccountNumber());
		if (bankAccount != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.ACCOUNT_NUMBER,
					bankAccountDTO.getAccountNumber());
			values.put(DBHelper.BALANCE, bankAccountDTO.getBalance());
			values.put(DBHelper.OPEN_TIME_STAMP, bankAccountDTO.getTime());
			result = db.insert(DBHelper.TABLE_ACCOUNT, null, values);
		}
		if (result != -1)
			return true;
		else
			return false;
	}

	public boolean update(BankAccountDTO bankAccountDTO) {
		ContentValues values = new ContentValues();
		values.put(DBHelper.ACCOUNT_NUMBER, bankAccountDTO.getAccountNumber());
		values.put(DBHelper.BALANCE, bankAccountDTO.getBalance());
		values.put(DBHelper.OPEN_TIME_STAMP, bankAccountDTO.getTime());
		int result = db.update(DBHelper.TABLE_ACCOUNT, values,
				DBHelper.ACCOUNT_NUMBER + " = ?",
				new String[] { bankAccountDTO.getAccountNumber() });
		if (result > 0)
			return true;
		else
			return false;
	}

	public int getTotalRecord() {
		String countQuery = "SELECT COUNT (*) FROM " + DBHelper.TABLE_ACCOUNT;
		Cursor cursor = db.rawQuery(countQuery, null);
		int totals = cursor.getInt(0);
		return totals;
	}

	public BankAccountDTO getBankAccountDTO(String accountNumber) {
		BankAccountDTO bankAccount = new BankAccountDTO();
		Cursor cursor = db.query(DBHelper.TABLE_ACCOUNT, new String[] {
				DBHelper.KEY_ID, DBHelper.ACCOUNT_NUMBER, DBHelper.BALANCE,
				DBHelper.OPEN_TIME_STAMP }, DBHelper.ACCOUNT_NUMBER + "=?",
				new String[] { accountNumber }, null, null, null);
		if (cursor.moveToFirst()) {
			bankAccount.setAccountNumber(cursor.getString(1));
			bankAccount.setDesc(cursor.getString(3));
			bankAccount.setBalance(cursor.getLong(2));
			bankAccount.setTime(cursor.getLong(4));
		}
		return bankAccount;
	}
}
