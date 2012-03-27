/*
 * This class is the Database Access Object. It is the point of contact for the front-end to access the database
 */
package edu.upenn.cis350.voice.db;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	// Database fields
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	
	//Instantiates the dbHelper
	public DBManager(Context context){
		dbHelper = new DBHelper(context);
	}
	//Opens up the database
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	//Closes the database
	public void close() {
		dbHelper.close();
	}
	
	//Method to add entries
	public void insert(String entry){
		
	}
	
	//Method to delete entries
	public void delete(String entry){
		
	}
	
	//Method to get all entries
	public List<String> getAll(){
		return null;
	}
}
