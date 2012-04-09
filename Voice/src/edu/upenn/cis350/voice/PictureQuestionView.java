package edu.upenn.cis350.voice;

import android.util.AttributeSet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PictureQuestionView extends LinearLayout implements VoiceViewI{
	private int answerNum;
	private ImageButton button1, button2, button3, button4;
	
	public PictureQuestionView(Context c) {
		super(c);
		button1 = new ImageButton(c);
		button2 = new ImageButton(c);
		button3 = new ImageButton(c);
		button4 = new ImageButton(c);
		addButtons();
	}
	
	public PictureQuestionView(Context c, AttributeSet a) {
		super(c, a);
		button1 = new ImageButton(c, a);
		button2 = new ImageButton(c, a);
		button3 = new ImageButton(c, a);
		button4 = new ImageButton(c, a);
		addButtons();
	}
	
	/**
	 * Add each button to this view.
	 */
	private void addButtons(){
		this.addView(button1);
		this.addView(button2);
		this.addView(button3);
		this.addView(button4);
		
		Bitmap pic = BitmapFactory.decodeResource(this.getResources(), R.drawable.happypup);
		
		button1.setImageBitmap(pic);
		button2.setImageBitmap(pic);
		button3.setImageBitmap(pic);
		button4.setImageBitmap(pic);
	}
	
	/**
	 * Find which button has been pressed, and return an assigned integer value based upon the chosen button.
	 */
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		
		if( eventAction == MotionEvent.ACTION_DOWN){

			if( button1.isPressed()){
				answerNum = 0;
			}
			else if( button2.isPressed()){
				answerNum = 4;
			}
			else if(button3.isPressed()){
				answerNum = 6;
			}
			else if(button4.isPressed()){
				answerNum = 10;
			}
		}
		return true;
	}
	

	public int getAnswer(){
		return answerNum;
	}

	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}
}