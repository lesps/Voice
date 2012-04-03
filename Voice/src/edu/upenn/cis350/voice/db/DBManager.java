/*
 * This class is the Database Access Object. It is the point of contact for the front-end to access the database
 */
package edu.upenn.cis350.voice.db;

import java.util.ArrayList;
import java.util.List;

import edu.upenn.cis350.voice.Question;
import edu.upenn.cis350.voice.Type;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	// Database fields
	private SQLiteDatabase database;
	private DBHelper dbHelper;
	private String[] querycolumns= {"question", "type"};
	
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
	
	//Method to add questions. Returns -1 if not successful
	public long insertQuestion(Question entry){
		ContentValues values = new ContentValues();
//		Log.i("debug message", "insert " + entry.getText()+ " into question");
		values.put("question", entry.getText());
		values.put("type", entry.getType().name());
//		Log.i("content values", values.toString());
		return database.insert(DBHelper.QUS_TABLE, null, values);
	}
	
	//Method to delete entries
	public void deleteQuestion(Question entry){
		database.delete(DBHelper.QUS_TABLE, "question = " + entry.getText() , null);
	}
	
	//Method to get all questions of a certain type
	public ArrayList<Question> getQuestionsOfType(Type t){
		ArrayList<Question> questions= new ArrayList<Question>();
		Cursor cursor = database.query(DBHelper.QUS_TABLE, querycolumns, "type = " + t.name(), null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Question q = cursorToQuestion(cursor);
			questions.add(q);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return questions;
	}
	
	//Method to get all questions
	public ArrayList<Question> getAllQuestions(){
		ArrayList<Question> questions= new ArrayList<Question>();
		Cursor cursor = database.query(DBHelper.QUS_TABLE, querycolumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Question q = cursorToQuestion(cursor);
			questions.add(q);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return questions;
	}
	
	//Converts values in our cursor to 
	private Question cursorToQuestion(Cursor cursor){
		//Retreive text of question
		String text= cursor.getString(0);
		//Retreive type of question
		Type type = Type.valueOf(cursor.getString(1));
		Question q = new Question(text, type);
		return q;
	}
}
