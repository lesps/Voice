/*
 * This class is the Database Access Object. It is the point of contact for the front-end to access the database
 */
package edu.upenn.cis350.voice.db;

import java.util.ArrayList;

import edu.upenn.cis350.voice.Question;
import edu.upenn.cis350.voice.Type;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
	
	/**
	 * Opens the database
	 * @throws SQLException If not successful
	 */
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Closes the database
	 */
	public void close() {
		dbHelper.close();
	}
	
	/**
	 * Add a question to the database
	 * @param entry The entry to be added to the database
	 * @return The output from the database (-1 if not successful)
	 */
	public long insertQuestion(Question entry){
		ContentValues values = new ContentValues();
		values.put("number", entry.getNumber());
		values.put("question", entry.getText());
		values.put("type", entry.getType().name());
		return database.insert(DBHelper.QUS_TABLE, null, values);
	}
	
	/**
	 * Insert an answer into the database
	 * @param cachenum The cache number
	 * @param answer A CSV string representing all the answers
	 * @return The output from the database (-1 if not successful)
	 */
	public long insertAnswer(int cachenum, String answer){
		ContentValues values = new ContentValues();
		values.put("cachenum", cachenum);
		values.put("answer", answer);
		return database.insert(DBHelper.ANS_TABLE, null, values);
	}
	
	/**
	 * Delete a particular question from the database
	 * @param entry The question to be deleted
	 */
	public void deleteQuestion(Question entry){
		database.delete(DBHelper.QUS_TABLE, "question = " + entry.getText() , null);
	}

	/**
	 * Delete all questions from the database
	 */
	public void deleteAllQuestions(){
		database.delete(DBHelper.QUS_TABLE, null , null);
	}
	
	/**
	 * Delete all answers from the database
	 */
	public void deleteAllAnswers(){
		database.delete(DBHelper.ANS_TABLE, null, null);
	}
	
	/**
	 * Get all questions of type t
	 * @param t The type to be gotten
	 * @return An array list of questions of type T
	 */
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
	
	/**
	 * Get a string-based list of all the answers
	 * @return An array list containing all the answers
	 */
	public ArrayList<String> getAllAnswers(){
		ArrayList<String> answers = new ArrayList<String>();
		Cursor cursor = database.query(DBHelper.ANS_TABLE, queryColsAns, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String s = cursor.getString(1);
			answers.add(s);
			cursor.moveToNext();
		}
		cursor.close();
		return answers;
	}
	
	/**
	 * Gets the cache size
	 * @return The size of the answer cache
	 */
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
