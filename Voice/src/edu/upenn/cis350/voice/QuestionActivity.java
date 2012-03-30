package edu.upenn.cis350.voice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	
	public static final int ACTIVITY_NewQuestion = 1;
	
	private ArrayList<Question> questionList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        questionList = new ArrayList<Question>();
        //TODO Import questions from the database object, store these in questionList
        for(int i = 0; i < questionList.size(); i++)
        	switchQuestion(questionList.get(i));
    }	
	
	
	public void switchQuestion(Question q){
		
		Intent i = new Intent(this, NewQuestionEvent.class);
		i.putExtra("Type", q.getType());
		i.putExtra("Text", q.getText());
		startActivityForResult(i, QuestionActivity.ACTIVITY_NewQuestion);
	}
}
