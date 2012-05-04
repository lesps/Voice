package edu.upenn.cis350.voice;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import edu.upenn.cis350.voice.db.DBManager;
import edu.upenn.cis350.voice.db.VoiceCallback;

import android.app.Activity;
import com.parse.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.database.sqlite.SQLiteException;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class WelcomeActivity extends Activity {

	private DBManager _db;

	private Timer synchTimer = new Timer();
	
	/**
	 *Called when the activity is first created. Initializes parse and synchronizes
	 *with parse.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Initialize the connection to parse
		Parse.initialize(this, "pPpqQgvXPcLhyGUNTD7ktBhXEsMWVybkyq89kamw",
		"5GMEKejNwtcqJBSO6G4gcvjbD2mC6dgMi3XdgnQY");
		try{
			_db = new DBManager(this);
		} catch(SQLiteException e){}
		setContentView(R.layout.welcome);
		
		//Perform a sync with parse on application load
		synchronizeQuestions();
		synchronizeAnswers();
		
		//Sync with parse every 3 minutes 
		long interval = 3*1000;
		synchTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				AutoSync();
			}
		}, 0,interval);
	}
	
	/**
	 * Automatically sync with parse
	 */
	private void AutoSync()
	{
		this.runOnUiThread(new Runnable() {
			public void run() {
				synchronizeQuestions();
				synchronizeAnswers();
			}
		});
	}
	
	/**
	 * Parse out individual answers from a comma separated string
	 * @param ans A comma-separated string representing all the answers
	 * @return An ArrayList containing the answers to the questions in order
	 */
	public ArrayList<Integer> parseAnswer(String ans){
		Scanner scan = new Scanner(ans);
		scan.useDelimiter(","); //For CSV string
		ArrayList<Integer> answers = new ArrayList<Integer>();
		while(scan.hasNext())
			answers.add(Integer.parseInt(scan.next()));
		return answers;
	}
	
	/**
	 * Synchronize answers with the Parse database as a series of columns of the 
	 * form Answer_X, where X is the number of the question
	 */
	public void synchronizeAnswers(){
		try{
			_db.open();
			ParseObject syncAnswer;
			ArrayList<String> cachedAnswers = _db.getAllAnswers();
			for(String ans : cachedAnswers){
				syncAnswer = new ParseObject("Answers");
				ArrayList<Integer> ansList = parseAnswer(ans);
				for(int i = 0; i < ansList.size(); ++i)
					syncAnswer.put("Answer_" + (i+1), ansList.get(i));
				syncAnswer.saveInBackground();
			}
			_db.deleteAllAnswers();
			_db.close();
		}catch(SQLiteException e){}
	}

	/**
	 * Construct a question from a ParseObject
	 * @param p	The ParseObject to get the question from
	 * @return A constructed question object
	 */
	public Question constructQuestion(ParseObject p){
		String text = p.getString("Text");
		if(text==null)
			return null;
		int num = p.getInt("Number");
		if(num==0)
			return null;
		Type t = null;
		String type = p.getString("Type");
		if(type==null)
			return null;
		if(type.equalsIgnoreCase("button"))
			t = Type.BUTTON;
		else if(type.equalsIgnoreCase("wheel"))
			t = Type.WHEEL;
		else if(type.equalsIgnoreCase("drag"))
			t = Type.DRAG;
		else if(type.equalsIgnoreCase("slider"))
			t = Type.SLIDER;
		else if(type.equalsIgnoreCase("picture"))
			t = Type.PICTURE;
		else
			return null;
		return new Question(num, text, t);
	}

	/**
	 * Synchronize questions from Parse with the local database
	 */
	public void synchronizeQuestions(){
		ParseQuery query = new ParseQuery("Question");
		VoiceCallback callback = new VoiceCallback(this);
		query.findInBackground(callback);
		
	}
	
	/**
	 * Update questions in the database after the callback finishes executing
	 * @param list The list of parse objects returned by the callback file
	 */
	public void updateQuestions(List<ParseObject> list){
		ArrayList<Question> qlist = new ArrayList<Question>();
		Iterator<ParseObject> iter = list.iterator();
		while(iter.hasNext()){
			Question q = constructQuestion(iter.next());
			if(q!=null)
				qlist.add(q);
		}
		try{	
			_db.open();
			_db.deleteAllQuestions();
			for(Question q : qlist)
				_db.insertQuestion(q);
			_db.close();
		}catch(SQLiteException e){
			e.printStackTrace();
		}
	}

	public void onProviderClick(View view){
		//Create and intent using the new activity and the class to be created (A quiz activity)
		Intent provider_Intent = new Intent(WelcomeActivity.this, ProviderInformationActivity.class);

		startActivity(provider_Intent);
	}
	
	public void onContinueClick(View view){
		//Create and intent using the new activity and the class to be created (A quiz activity)
		Intent question_Intent = new Intent(WelcomeActivity.this, QuestionActivity.class);

		startActivity(question_Intent);
	}

}
