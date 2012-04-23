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
		
		_superHappy = setUp.createScaledBitmap(_superHappy, 100, 77, false);
		_happy = setUp.createScaledBitmap(_happy, 100, 77, false);
		_neutral = setUp.createScaledBitmap(_neutral, 100, 77, false);
		_sad = setUp.createScaledBitmap(_sad, 100, 77, false);;
		_crying = setUp.createScaledBitmap(_crying, 100, 77, false);
		_pressedSuperHappy = setUp.createScaledBitmap(_pressedSuperHappy, 100, 77, false);
		_pressedHappy = setUp.createScaledBitmap(_pressedHappy, 100, 77, false);
		_pressedNeutral = setUp.createScaledBitmap(_pressedNeutral, 100, 77, false);
		_pressedSad = setUp.createScaledBitmap(_pressedSad, 100, 77, false);
		_pressedCrying = setUp.createScaledBitmap(_pressedCrying, 100, 77, false);
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(255, 48, 48);
		if (_selected == _superHappy)
			canvas.drawBitmap(_pressedSuperHappy, 400, 160, null);
		else
			canvas.drawBitmap(_superHappy, 400, 160, null);
		if (_selected == _happy)
			canvas.drawBitmap(_pressedHappy, 560, 160, null);
		else
			canvas.drawBitmap(_happy, 560, 160, null);
		if (_selected == _neutral)
			canvas.drawBitmap(_pressedNeutral, 720, 160, null);
		else
			canvas.drawBitmap(_neutral, 720, 160, null);
		if (_selected == _sad)
			canvas.drawBitmap(_pressedSad, 500, 320, null);
		else
			canvas.drawBitmap(_sad, 500, 320, null);
		if (_selected == _crying)
			canvas.drawBitmap(_pressedCrying, 660, 320, null);
		else
			canvas.drawBitmap(_crying, 660, 320, null);
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
			if (x >= 400 && x <= 500 && y >= 160 && y <= 260) { 		//SuperHappy
				_selected = _superHappy;
				return true;
			}
			else if (x >= 560 && x <= 660 && y >= 160 && y <= 260) { 	//Happy
				_selected = _happy;
				return true;
			}
			else if (x >= 720 && x <= 820 && y >= 160 && y <= 260) {	//Neutral
				_selected = _neutral;
				return true;
			}
			else if (x >= 500 && x <= 600 && y >= 320 && y <= 396) {	//Sad
				_selected = _sad;
				return true;
			}
			else if (x >= 660 && x <= 760 && y >= 320 && y <= 396) {	//Crying
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
	
	public void animate(){
		
	}
}
