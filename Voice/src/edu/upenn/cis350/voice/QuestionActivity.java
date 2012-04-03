package edu.upenn.cis350.voice;

import java.util.ArrayList;

import edu.upenn.cis350.voice.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

public class QuestionActivity extends Activity {

	public static final int ACTIVITY_NewQuestion = 1;
	public static final int ACTIVITY_End = 2;
	private DBManager dataManager;

	private int totalScore;

	private ArrayList<Question> questionList;
	private Question currentQuestion;
	private int numQuestion;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		totalScore = 0; 
		questionList = new ArrayList<Question>();
//		questionList.add(new Question("stuff", Type.DRAG));
//		questionList.add(new Question("tools", Type.WHEEL));
		//Creates an instance of the Data Access Object
		try{
			dataManager= new DBManager(this);
			dataManager.open();
			//IMPORTANT: The database stores all questions stored in all previous runs of the app
			//Uncomment the delete line if you don't want the database to keep building up
			dataManager.deleteAll(); //Clears the database
			dataManager.insertQuestion(new Question("stuff", Type.DRAG));
			dataManager.insertQuestion(new Question("tools", Type.WHEEL));
			questionList = dataManager.getAllQuestions();
		    dataManager.close();
		}catch(SQLiteException e){
			//Catching exception so the app doesn't crash and just goes to the thank you screen
		}
		numQuestion = -1;
		switchQuestion(true);
	}	


	public void switchQuestion(boolean next){
		if(next){
			++numQuestion;
			if(numQuestion >= questionList.size()){
				terminate();
				return;
			}
		}
		else{
			--numQuestion;
			if(numQuestion < 0)
				numQuestion = 0;
		}
		currentQuestion = questionList.get(numQuestion);
		Intent i = new Intent(this, NewQuestionActivity.class);
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
		}
	}

	public void terminate(){
		Intent i = new Intent(this, ThankYouActivity.class);
		i.putExtra("totalScore", this.totalScore);
		startActivity(i);
	}
}
