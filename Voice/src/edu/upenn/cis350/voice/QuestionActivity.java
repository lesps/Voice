package edu.upenn.cis350.voice;

import java.util.ArrayList;

import com.parse.Parse;

import edu.upenn.cis350.voice.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

public class QuestionActivity extends Activity {

	public static final int ACTIVITY_NewQuestion = 1;
	public static final int ACTIVITY_Confirmation = 2;
	public static final int ACTIVITY_End = 3;
	private DBManager dataManager;

	private int totalScore;

	private ArrayList<Question> questionList;
	private Question currentQuestion;
	private int numQuestion;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initialize the PARSE object
		Parse.initialize(this, "pPpqQgvXPcLhyGUNTD7ktBhXEsMWVybkyq89kamw", 
				"5GMEKejNwtcqJBSO6G4gcvjbD2mC6dgMi3XdgnQY"); 
		totalScore = 0; 
		questionList = new ArrayList<Question>();
		questionList.add(new Question("How do you feel right now?", Type.DRAG));
		questionList.add(new Question("How was your day today?", Type.BUTTON));
		questionList.add(new Question("How would you rate the quality of your care?", Type.SLIDER));
		//Creates an instance of the Data Access Object
		/**
		try{
			dataManager= new DBManager(this);
			dataManager.open();
			//IMPORTANT: The database stores all questions stored in all previous runs of the app
			//Uncomment the delete line if you don't want the database to keep building up
			dataManager.deleteAll(); //Clears the database
			questionList = dataManager.getAllQuestions();
		    dataManager.close();
		}catch(SQLiteException e){
			//Catching exception so the app doesn't crash and just goes to the thank you screen
		}
		**/
		numQuestion = -1;
		switchQuestion(true);
	}	


	public void switchQuestion(boolean next){
		if(next){
			++numQuestion;
			if(numQuestion >= questionList.size()){
				Intent i = new Intent(this, ConfirmationActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_Confirmation);
				return;
			}
		}
		else{
			--numQuestion;
			if(numQuestion < 0)
				numQuestion = 0;
		}
		currentQuestion = questionList.get(numQuestion);
		Intent i = new Intent(this, DisplayQuestion.class);
		i.putExtra("Type", currentQuestion.getType());
		i.putExtra("Text", currentQuestion.getText());
		startActivityForResult(i, QuestionActivity.ACTIVITY_NewQuestion);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);

		switch(requestCode){
		case ACTIVITY_NewQuestion:
			Integer answer = (Integer)intent.getExtras().get("Answer");
			currentQuestion.setAnswer(answer);
			switchQuestion(intent.getExtras().getBoolean("NextQuestion"));
			break;
		case ACTIVITY_Confirmation:
			boolean submit = (Boolean)intent.getExtras().get("Submit");
			if(submit)
				terminate();
			else
				switchQuestion(false);
		}
	}
	
	/**
	 * Store information in the database and pass control to the thank you activity
	 */
	public void terminate(){
		/**
		 * Place code to synchronize with Parse here
		 */
		Intent i = new Intent(this, ThankYouActivity.class);
		startActivity(i);
	}
}
