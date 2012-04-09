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
	private String[] querycolumns= {"number", "question", "type"};
	private String[] queryColsAns = {"cachenum", "answer"};
	
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
		values.put("number", entry.getNumber());
		values.put("question", entry.getText());
		values.put("type", entry.getType().name());
		return database.insert(DBHelper.QUS_TABLE, null, values);
	}
	
	public long insertAnswer(int cachenum, String answer){
		ContentValues values = new ContentValues();
		values.put("cachenum", cachenum);
		values.put("answer", answer);
		return database.insert(DBHelper.ANS_TABLE, null, values);
	}
	
	//Method to delete entries
	public void deleteQuestion(Question entry){
		database.delete(DBHelper.QUS_TABLE, "question = " + entry.getText() , null);
	}

	//Method to delete all questions
	public void deleteAllQuestions(){
		database.delete(DBHelper.QUS_TABLE, null , null);
	}
	
	//Method to delete all answers
	public void deleteAllAnswers(){
		database.delete(DBHelper.ANS_TABLE, null, null);
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
	
	/**
	 * This method retrieves all the questions stored in the SQLite Database
	 * 
	 * @param none
	 * @return - Returns an ArrayList of Questions. 
	 */	
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
	
	//Method to get all answers
	public ArrayList<String> getAllAnswers(){
		ArrayList<String> answers = new ArrayList<String>();
		Log.v("DEBUG", "1");
		Cursor cursor = database.query(DBHelper.ANS_TABLE, queryColsAns, null, null, null, null, null);
		Log.v("DEBUG", "2");
		cursor.moveToFirst();
		Log.v("DEBUG", "3");
		while(!cursor.isAfterLast()){
			String s = cursor.getString(1);
			answers.add(s);
			cursor.moveToNext();
		}
		Log.v("DEBUG", "4");
		cursor.close();
		return answers;
	}
	
	//Method to get current number of answers
	public int getAnswerCacheSize(){
		Cursor cursor = database.query(DBHelper.ANS_TABLE, queryColsAns, null, null, null, null, null);
		int num = 0;
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			++num;
			cursor.moveToNext();
		}
		cursor.close();
		return num;
	}
	
	/**
	 * This method is a helper method that converts the values of a standard SQL cursor into our
	 * Question Object. Our Question object has 3 fields (number, text, and type)
	 * 
	 * @param cursor- The cursor that represents the values in a row of our SQL table
	 * @return - Returns a question created from the values 
	 */	
	private Question cursorToQuestion(Cursor cursor){
		//Retrieve number of question
		Integer number = Integer.parseInt(cursor.getString(0));
		//Retrieve text of question
		String text= cursor.getString(1);
		//Retrieve type of question
		Type type = Type.valueOf(cursor.getString(2));
		Question q = new Question(number, text, type);
		return q;
	}
}
