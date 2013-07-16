package com.example.bankaccountdao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "bankAccount.sql";

	public static final String KEY_ID = "id";
	public static final String ACCOUNT_NUMBER = "accountNumber";

	public static final String TABLE_ACCOUNT = "Account";
	public static final String BALANCE = "balance";
	public static final String OPEN_TIME_STAMP = "openTimestamp";

	public static final String TABLE_TRANSACTION = "Transactions";
	public static final String AMOUNT = "amount";
	public static final String DESCRIPTION = "description";

	private static final int VERSION = 1;

	private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
			+ TABLE_ACCOUNT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_NUMBER
			+ " TEXT, " + BALANCE + " LONG," + OPEN_TIME_STAMP + " LONG);";

	private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE "
			+ TABLE_TRANSACTION + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACCOUNT_NUMBER + " TEXT"
			+ "," + AMOUNT + " LONG," + OPEN_TIME_STAMP + " long,"
			+ DESCRIPTION + " TEXT);";

	public DBHelper(Context context, String databaseName) {
		super(context, null, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
