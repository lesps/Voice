package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

public class WheelQuestionView extends VoiceView {

	public WheelQuestionView(Context c) {
		super(c);
	}
	public WheelQuestionView(Context c, AttributeSet a) {
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

	public int getAnswer(){
		return 0;
	}
}
