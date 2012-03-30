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
	private Question currentQuestion;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        questionList = new ArrayList<Question>();
        //TODO Import questions from the database object, store these in questionList
        
        
    }	
	
	
	public void switchQuestion(){
		
		Intent i;
		switch(currentQuestion.getType()){
		
			case BUTTON:
				i = new Intent(this, ButtonQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_ButtonQuestion);
				break;
			case SLIDER:
				i = new Intent(this, SliderQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_SliderQuestion);
			case DRAG:
				i = new Intent(this, DragQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_DragQuestion);
				break;
			case PICTURE:
				i = new Intent(this, PictureQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_PictureQuestion);
				break;
			case WHEEL:
				i = new Intent(this, WheelQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_WheelQuestion);
				break;
			case DRAWING:
				i = new Intent(this, DrawingQuestionActivity.class);
				startActivityForResult(i, QuestionActivity.ACTIVITY_DrawingQuestion);
				break;
		}
	}
}
