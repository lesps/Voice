/*
 * This is the class extending SQLiteOpenHelper, and is responsible for creating the database.
 * The onUpdate() method (for now) deletes the existing data and re-creates the table
 * 
 */


package edu.upenn.cis350.voice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	//Database name
	public static final String DB_NAME = "voice";
	//Names of tables
	public static final String QUS_TABLE = "questions";
	public static final String ANS_TABLE = "answers";

	private static final int DATABASE_VERSION = 1;
	
	//Statement to create questions table
	private static final String DATABASE_CREATE = 
			"create table " + QUS_TABLE +
			"(number smallint, question varchar, type varchar);";
	
	
	//Statement to create answers table
	private static final String ANSWERS_DATABASE_CREATE = 
			"create table " + ANS_TABLE +
			"(cachenum smallint, answer varchar)";

	//Context must be the activity that creates the database.
	public DBHelper(Context context) {
		//generic constructor for SQLiteOpenHelper
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Creates the SQLite table if it does not already exist
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Executes query to create db
		db.execSQL(DATABASE_CREATE);
		db.execSQL(ANSWERS_DATABASE_CREATE);
	}

	/**
	 * Handles upgrade commands for SQLite database
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//drops db and creates a new one
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME + ";");
		onCreate(db);
	}

}
