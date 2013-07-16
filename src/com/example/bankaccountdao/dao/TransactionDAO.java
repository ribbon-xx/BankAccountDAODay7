package com.example.bankaccountdao.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TransactionDAO {
	private SQLiteDatabase db;

	public TransactionDAO(Context context, String name) {
		DBHelper openHelper = new DBHelper(context, name);
		this.db = openHelper.getWritableDatabase();
	}

	public long insert(TransactionDTO transaction) {
		ContentValues values = new ContentValues();
		values.put(DBHelper.ACCOUNT_NUMBER, transaction.getAccountNumber());
		values.put(DBHelper.AMOUNT, transaction.getAmount());
		values.put(DBHelper.OPEN_TIME_STAMP, transaction.getTime());
		values.put(DBHelper.DESCRIPTION, transaction.getDesc());
		return db.insert(DBHelper.TABLE_TRANSACTION, null, values);
	}

	public ArrayList<TransactionDTO> getListOfTransactionDTO(
			String accountNumber) {
		ArrayList<TransactionDTO> alTransaction = new ArrayList<TransactionDTO>();
		Cursor cursor = db.query(DBHelper.TABLE_TRANSACTION, new String[] {
				DBHelper.KEY_ID, DBHelper.ACCOUNT_NUMBER, DBHelper.AMOUNT,
				DBHelper.OPEN_TIME_STAMP, DBHelper.DESCRIPTION },
				DBHelper.ACCOUNT_NUMBER + " =? ",
				new String[] { accountNumber }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setAccountNumber(cursor.getString(cursor
						.getColumnIndex(DBHelper.ACCOUNT_NUMBER)));
				transactionDTO.setAmount(cursor.getLong(cursor
						.getColumnIndex(DBHelper.AMOUNT)));
				transactionDTO.setDesc(cursor.getString(cursor
						.getColumnIndex(DBHelper.DESCRIPTION)));
				transactionDTO.setTime(cursor.getLong(cursor
						.getColumnIndex(DBHelper.OPEN_TIME_STAMP)));
				alTransaction.add(transactionDTO);
			} while (cursor.moveToNext());
		}
		return alTransaction;
	}

	public ArrayList<TransactionDTO> getListOfTransactionDTO(
			String accountNumber, long startTime, long stopTime) {
		ArrayList<TransactionDTO> alTransaction = new ArrayList<TransactionDTO>();
		String[] columns = new String[] { DBHelper.KEY_ID,
				DBHelper.ACCOUNT_NUMBER, DBHelper.AMOUNT,
				DBHelper.OPEN_TIME_STAMP, DBHelper.DESCRIPTION };
		String whereClause = DBHelper.ACCOUNT_NUMBER + " =? and "
				+ DBHelper.OPEN_TIME_STAMP + " >=? and "
				+ DBHelper.OPEN_TIME_STAMP + " <=? ";
		String[] whereArgs = new String[] { accountNumber,
				String.valueOf(startTime), String.valueOf(stopTime) };
		Cursor cursor = db.query(DBHelper.TABLE_TRANSACTION, columns,
				whereClause, whereArgs, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setAccountNumber(cursor.getString(cursor
						.getColumnIndex(DBHelper.ACCOUNT_NUMBER)));
				transactionDTO.setAmount(cursor.getLong(cursor
						.getColumnIndex(DBHelper.AMOUNT)));
				transactionDTO.setDesc(cursor.getString(cursor
						.getColumnIndex(DBHelper.DESCRIPTION)));
				transactionDTO.setTime(cursor.getLong(cursor
						.getColumnIndex(DBHelper.OPEN_TIME_STAMP)));
				alTransaction.add(transactionDTO);
			} while (cursor.moveToNext());
		}
		return alTransaction;
	}

	public ArrayList<TransactionDTO> getListOfTransactionDTO(
			String accountNumber, int n) {
		ArrayList<TransactionDTO> alTransaction = new ArrayList<TransactionDTO>();
		String countQuery = "SELECT * FROM " + DBHelper.TABLE_TRANSACTION
				+ " WHERE " + DBHelper.ACCOUNT_NUMBER + " = '" + accountNumber
				+ "' LIMIT " + n;
		Cursor cursor = db.rawQuery(countQuery, null);
		if (cursor.moveToFirst()) {
			do {
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setAccountNumber(cursor.getString(cursor
						.getColumnIndex(DBHelper.ACCOUNT_NUMBER)));
				transactionDTO.setAmount(cursor.getLong(cursor
						.getColumnIndex(DBHelper.AMOUNT)));
				transactionDTO.setDesc(cursor.getString(cursor
						.getColumnIndex(DBHelper.DESCRIPTION)));
				transactionDTO.setTime(cursor.getLong(cursor
						.getColumnIndex(DBHelper.OPEN_TIME_STAMP)));
				alTransaction.add(transactionDTO);
			} while (cursor.moveToNext());
		}
		return alTransaction;
	}
}