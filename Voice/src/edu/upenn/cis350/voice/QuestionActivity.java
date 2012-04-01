package edu.upenn.cis350.voice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	
	public static final int ACTIVITY_NewQuestion = 1;
	public static final int ACTIVITY_End = 2;
	
	private int totalScore;
	
	private ArrayList<Question> questionList;
	private Question currentQuestion;
	private int numQuestion;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalScore = 0; 
        questionList = new ArrayList<Question>();
        
        //TODO Import questions from the database object, store these in questionList
        numQuestion = 0;
        switchQuestion(true);
    }	
	
	
	public void switchQuestion(boolean next){
		if(++numQuestion >= questionList.size())
			terminate();
		Intent i = new Intent(this, NewQuestionEvent.class);
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
