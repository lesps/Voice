package edu.upenn.cis350.voice;

import android.util.AttributeSet;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

public class PictureQuestionView extends ImageButton {
	private int answerNum;
	
	public PictureQuestionView(Context c) {
		super(c);
	}
	
	public PictureQuestionView(Context c, AttributeSet a) {
		super(c, a);
	}
	
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		
		if( eventAction == MotionEvent.ACTION_DOWN){
			if( this.getTag().equals("one")){
				answerNum = 1;
			}
			else if( this.getTag().equals("two")){
				answerNum = 2;
			}
			else if( this.getTag().equals("three")){
				answerNum = 3;
			}
			else if( this.getTag().equals("four")){
				answerNum = 4;
			}
		}
		return true;
	}
	

	public int getAnswer(){
		return answerNum;
	}

}
