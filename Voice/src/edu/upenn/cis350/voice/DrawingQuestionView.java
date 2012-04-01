package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.content.Context;

public class DrawingQuestionView extends VoiceView {

	public DrawingQuestionView(Context c) {
		super(c);
	}
	
	public DrawingQuestionView(Context c, AttributeSet a) {
		super(c, a);
	}
	
	protected void onDraw(Canvas canvas) {

	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		
		invalidate();
		return true;
	}
	
	@Override
	public int getAnswer(){
		return 0;
	}

}
