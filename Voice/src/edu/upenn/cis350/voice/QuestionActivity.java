package edu.upenn.cis350.voice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	
	public static final int ACTIVITY_ButtonQuestion = 1;
	public static final int ACTIVITY_SliderQuestion = 2;
	public static final int ACTIVITY_DrawingQuestion = 3;
	public static final int ACTIVITY_PictureQuestion = 4;
	public static final int ACTIVITY_WheelQuestion = 5;
	public static final int ACTIVITY_DragQuestion = 6;
	
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
		
		Intent i;
			
	}
}
