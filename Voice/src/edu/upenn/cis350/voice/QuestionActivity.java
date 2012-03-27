package edu.upenn.cis350.voice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	
	public static final int ACTIVITY_ButtonQuestion = 1;
	
	private ArrayList<Question> questionList;
	private Question currentQuestion;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        questionList = new ArrayList<Question>();
        //TODO Import questions from the database object, store these in questionList
        
        
    }	
	
	
	public void switchQuestion(){
		
		switch(currentQuestion.getType()){
		
			case BUTTON:
				Intent i = new Intent(this, ButtonQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_ButtonQuestion);
				break;
		}
	}
}
