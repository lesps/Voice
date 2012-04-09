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
	
	//Method to get all answers
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
	
	//Converts values in our cursor to 
	private Question cursorToQuestion(Cursor cursor){
		//Retrieve number of question
		Integer number = Integer.parseInt(cursor.getString(0));
		//Retreive text of question
		String text= cursor.getString(1);
		//Retreive type of question
		Type type = Type.valueOf(cursor.getString(2));
		Question q = new Question(number, text, type);
		return q;
	}
}
