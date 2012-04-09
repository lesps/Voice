package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

public class WheelQuestionView extends View implements VoiceViewI {

	Bitmap _spinner, _selector;
	
	public WheelQuestionView(Context c) {
		super(c);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
		_selector = BitmapFactory.decodeResource(getResources(), R.drawable.selector);
	}
	public WheelQuestionView(Context c, AttributeSet a) {
		super(c, a);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
		_selector = BitmapFactory.decodeResource(getResources(), R.drawable.selector);
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(216, 134, 120);
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
	
	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}
}
