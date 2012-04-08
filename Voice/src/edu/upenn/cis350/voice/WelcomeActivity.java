package edu.upenn.cis350.voice;

import java.util.ArrayList;

import edu.upenn.cis350.voice.db.DBManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.database.sqlite.SQLiteException;

import com.parse.Parse;
import com.parse.ParseObject;

public class WelcomeActivity extends Activity {

	private DBManager _db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "pPpqQgvXPcLhyGUNTD7ktBhXEsMWVybkyq89kamw",
		"5GMEKejNwtcqJBSO6G4gcvjbD2mC6dgMi3XdgnQY");
		try{
			_db = new DBManager(this);
		} catch(SQLiteException e){}
		setContentView(R.layout.welcome);

	}

	public void synchronize(){
		synchronizeAnswers();
		synchronizeQuestions();
	}

	/**
	 * Synchronize with the Parse database
	 */
	public void synchronizeAnswers(){

		try{
			_db.open();
			ParseObject syncAnswer;
			ArrayList<String> cachedAnswers = _db.getAllAnswers();
			for(String ans : cachedAnswers){
				syncAnswer = new ParseObject("Answers");
				syncAnswer.put("Answer", ans);
				syncAnswer.saveInBackground();
			}
			_db.deleteAllAnswers();
			_db.close();
		}catch(SQLiteException e){}
	}

	public void synchronizeQuestions(){

	}

	public void onSynchronizeClick(View view){
		synchronize();
	}

	public void onContinueClick(View view){
		//Create and intent using the new activity and the class to be created (A quiz activity)
		Intent i = new Intent(WelcomeActivity.this, QuestionActivity.class);

		startActivity(i);
	}




}