package edu.upenn.cis350.voice;

import java.util.ArrayList;

import edu.upenn.cis350.voice.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	
	public static final int ACTIVITY_NewQuestion = 1;
    private DBManager dataManager;
	
	private ArrayList<Question> questionList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Creates an instance of the Data Access Object
        dataManager= new DBManager(this);
        dataManager.open();
        questionList = dataManager.getAllQuestions();
        dataManager.close();
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
