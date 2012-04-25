package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

public class ButtonQuestionView extends View implements VoiceViewI {
	
	private Bitmap _superHappy, _happy, _neutral, _sad, _crying, setUp;
	private Bitmap _pressedSuperHappy, _pressedHappy, _pressedNeutral, _pressedSad, _pressedCrying;
	private Bitmap _selected;
	private Bitmap lastDrawn;
	private boolean animate;
	private int width=200, height=154;
	
	public ButtonQuestionView(Context c) {
		super(c);
		setUp();
	}
	public ButtonQuestionView(Context c, AttributeSet a) {
		super(c, a);
		setUp();
	}
	
	//Sets up the buttons and resizes them
	private void setUp(){
		_superHappy = BitmapFactory.decodeResource(getResources(), R.drawable.happypup);
		_happy = BitmapFactory.decodeResource(getResources(), R.drawable.lesshappypup);
		_neutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutralpup);
		_sad = BitmapFactory.decodeResource(getResources(), R.drawable.sadpup);
		_crying = BitmapFactory.decodeResource(getResources(), R.drawable.verysadpup);
		_pressedSuperHappy = BitmapFactory.decodeResource(getResources(), R.drawable.happypup_pressed);
		_pressedHappy = BitmapFactory.decodeResource(getResources(), R.drawable.lesshappypup_pressed);
		_pressedNeutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutralpup_pressed);
		_pressedSad = BitmapFactory.decodeResource(getResources(), R.drawable.sadpup_pressed);
		_pressedCrying = BitmapFactory.decodeResource(getResources(), R.drawable.verysadpup_pressed);
		
		_superHappy = setUp.createScaledBitmap(_superHappy, width, height, false);
		_happy = setUp.createScaledBitmap(_happy, width, height, false);
		_neutral = setUp.createScaledBitmap(_neutral, width, height, false);
		_sad = setUp.createScaledBitmap(_sad, width, height, false);;
		_crying = setUp.createScaledBitmap(_crying, width, height, false);
		_pressedSuperHappy = setUp.createScaledBitmap(_pressedSuperHappy, width, height, false);
		_pressedHappy = setUp.createScaledBitmap(_pressedHappy, width, height, false);
		_pressedNeutral = setUp.createScaledBitmap(_pressedNeutral, width, height, false);
		_pressedSad = setUp.createScaledBitmap(_pressedSad, width, height, false);
		_pressedCrying = setUp.createScaledBitmap(_pressedCrying, width, height, false);
		
		animate = false;

	}
	
	protected void onDraw(Canvas canvas) {
		//Sets the canvas pastel yellow
		canvas.drawRGB(238, 221, 130);
		if(!animate){
		if (_selected == _superHappy)
			canvas.drawBitmap(_pressedSuperHappy, 300, 70, null);
		else
			canvas.drawBitmap(_superHappy, 300, 70, null);
		if (_selected == _happy)
			canvas.drawBitmap(_pressedHappy, 560, 70, null);
		else
			canvas.drawBitmap(_happy, 560, 70, null);
		if (_selected == _neutral)
			canvas.drawBitmap(_pressedNeutral, 820, 70, null);
		else
			canvas.drawBitmap(_neutral, 820, 70, null);
		if (_selected == _sad)
			canvas.drawBitmap(_pressedSad, 440, 290, null);
		else
			canvas.drawBitmap(_sad, 440, 290, null);
		if (_selected == _crying)
			canvas.drawBitmap(_pressedCrying, 700, 290, null);
		else
			canvas.drawBitmap(_crying, 700, 290, null);
		}
		if(animate){
			canvas.drawBitmap(lastDrawn, 470, 100, null);
		}
	}
	
	/**
	 * This method allows the user to press a button by touching it on the screen.
	 * 
	 * @param event - The corresponding MotionEvent object
	 * @return - Returns the boolean true if a button was pressed; false otherwise.
	 */
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		float x = event.getX();
		float y = event.getY();
		
		if (eventAction==MotionEvent.ACTION_DOWN) {
			if (x >= 300 && x <= 500 && y >= 70 && y <= 224) { 		//SuperHappy
				_selected = _superHappy;
				return true;
			}
			else if (x >= 560 && x <= 760 && y >= 160 && y <= 224) { 	//Happy
				_selected = _happy;
				return true;
			}
			else if (x >= 820 && x <= 1020 && y >= 160 && y <= 224) {	//Neutral
				_selected = _neutral;
				return true;
			}
			else if (x >= 440 && x <= 640 && y >= 380 && y <= 444) {	//Sad
				_selected = _sad;
				return true;
			}
			else if (x >= 700 && x <= 900 && y >= 380 && y <= 444) {	//Crying
				_selected = _crying;
				return true;
			}
			else return false;
		}
		
		invalidate();
		return false;
	}

	public int getAnswer(){
		if (_selected == _superHappy)
			return 10;
		else if (_selected == _happy)
			return 8;
		else if (_selected == _neutral)
			return 6;
		else if (_selected == _sad)
			return 4;
		else if (_selected == _crying)
			return 2;
		else return -1;
		
	}
	
	public void setAnswer(int prevAnswer){
		switch (prevAnswer) {
			case -1:
				return;
			case 10:
				_selected = _superHappy;
				return;
			case 8:
				_selected = _happy;
				return;
			case 6:
				_selected = _neutral;
				return;
			case 4:
				_selected = _sad;
				return;
			case 2:
				_selected = _crying;
				return;
			default:
				return;
		}
	}

	/**
	 * Moves all unselected images off the canvas, and blows up the selected image
	 * 
	 */
	public void animate() {
		animate = true;
		int answerInt = getAnswer();
		if (answerInt == 10) {
			_superHappy = BitmapFactory.decodeResource(getResources(), R.drawable.happypup);
			lastDrawn = setUp.createScaledBitmap(_superHappy, 2*width, 2*height, false);
		}
		else if (answerInt == 8) {
			_happy = BitmapFactory.decodeResource(getResources(), R.drawable.lesshappypup);
			lastDrawn = setUp.createScaledBitmap(_happy, 2*width, 2*height, false);
		}
		else if (answerInt == 6) {
			_neutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutralpup);
			lastDrawn = setUp.createScaledBitmap(_neutral, 2*width, 2*height, false);
		}
		else if (answerInt == 4) {
			_sad = BitmapFactory.decodeResource(getResources(), R.drawable.sadpup);
			lastDrawn = setUp.createScaledBitmap(_sad, 2*width, 2*height, false);
		}
		else if (answerInt == 2) {
			_crying = BitmapFactory.decodeResource(getResources(), R.drawable.verysadpup);
			lastDrawn = setUp.createScaledBitmap(_crying, 2*width, 2*height, false);
		}
		else{
			animate = false;
		}
		invalidate();

	}

}
