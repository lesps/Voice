package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

public class ButtonQuestionView extends View implements VoiceViewI {
	
	Bitmap _superHappy, _happy, _neutral, _sad, _crying, _angry;
	Bitmap _pressedSuperHappy, _pressedHappy, _pressedNeutral, _pressedSad, _pressedCrying, _pressedAngry;
	Bitmap _selected;
	
	public ButtonQuestionView(Context c) {
		super(c);
		_superHappy = BitmapFactory.decodeResource(getResources(), R.drawable.superhappy);
		_happy = BitmapFactory.decodeResource(getResources(), R.drawable.happy);
		_neutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutral);
		_sad = BitmapFactory.decodeResource(getResources(), R.drawable.sad);
		_crying = BitmapFactory.decodeResource(getResources(), R.drawable.crying);
		_angry = BitmapFactory.decodeResource(getResources(), R.drawable.angry);
		_pressedSuperHappy = BitmapFactory.decodeResource(getResources(), R.drawable.superhappy_pressed);
		_pressedHappy = BitmapFactory.decodeResource(getResources(), R.drawable.happy_pressed);
		_pressedNeutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutral_pressed);
		_pressedSad = BitmapFactory.decodeResource(getResources(), R.drawable.sad_pressed);
		_pressedCrying = BitmapFactory.decodeResource(getResources(), R.drawable.crying_pressed);
		_pressedAngry = BitmapFactory.decodeResource(getResources(), R.drawable.angry_pressed);
	}
	public ButtonQuestionView(Context c, AttributeSet a) {
		super(c, a);
		_superHappy = BitmapFactory.decodeResource(getResources(), R.drawable.superhappy);
		_happy = BitmapFactory.decodeResource(getResources(), R.drawable.happy);
		_neutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutral);
		_sad = BitmapFactory.decodeResource(getResources(), R.drawable.sad);
		_crying = BitmapFactory.decodeResource(getResources(), R.drawable.crying);
		_angry = BitmapFactory.decodeResource(getResources(), R.drawable.angry);
		_pressedSuperHappy = BitmapFactory.decodeResource(getResources(), R.drawable.superhappy_pressed);
		_pressedHappy = BitmapFactory.decodeResource(getResources(), R.drawable.happy_pressed);
		_pressedNeutral = BitmapFactory.decodeResource(getResources(), R.drawable.neutral_pressed);
		_pressedSad = BitmapFactory.decodeResource(getResources(), R.drawable.sad_pressed);
		_pressedCrying = BitmapFactory.decodeResource(getResources(), R.drawable.crying_pressed);
		_pressedAngry = BitmapFactory.decodeResource(getResources(), R.drawable.angry_pressed);
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(126, 93, 182);
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
			canvas.drawBitmap(_pressedSad, 400, 320, null);
		else
			canvas.drawBitmap(_sad, 400, 320, null);
		if (_selected == _crying)
			canvas.drawBitmap(_pressedCrying, 560, 320, null);
		else
			canvas.drawBitmap(_crying, 560, 320, null);
		if (_selected == _angry)
			canvas.drawBitmap(_pressedAngry, 720, 320, null);
		else
			canvas.drawBitmap(_angry, 720, 320, null);
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
			else if (x >= 400 && x <= 500 && y >= 320 && y <= 420) {	//Sad
				_selected = _sad;
				return true;
			}
			else if (x >= 560 && x <= 660 && y >= 320 && y <= 420) {	//Crying
				_selected = _crying;
				return true;
			}
			else if (x >= 720 && x <= 820 && y >= 320 && y <= 420) {	//Angry
				_selected = _angry;
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
		else if (_selected == _angry)
			return 0;
		else return -1;
		
	}
	
	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}
	
	public void animate(){
		
	}
}
