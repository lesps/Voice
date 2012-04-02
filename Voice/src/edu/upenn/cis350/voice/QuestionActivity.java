package edu.upenn.cis350.voice;

import java.util.ArrayList;

import edu.upenn.cis350.voice.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

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
//		questionList.add(new Question("stuff", Type.PICTURE));
//		questionList.add(new Question("tools", Type.DRAG));
		
		try{
			//Creates an instance of the Data Access Object
			dataManager= new DBManager(this);
			dataManager.open();
			//READ THIS PLZ: Uncomment the delete lines if you don't want the database to keep building up
			//with the 2 sample questions
//			dataManager.deleteQuestion(new Question("\"stuff\"", Type.PICTURE));
//			dataManager.deleteQuestion(new Question("\"tools\"", Type.DRAG));
			dataManager.insertQuestion(new Question("stuff", Type.PICTURE));
			dataManager.insertQuestion(new Question("tools", Type.DRAG));
			questionList = dataManager.getAllQuestions();
		    dataManager.close();
		    //Test to check whether db works
			for(Question q: questionList){
				Log.i("Question retrived from DB", "text = " + q.getText() + " type = "+ q.getType());
			}
		}catch (SQLiteException e){
			Log.i("exception", e.getMessage());
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
