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
			canvas.drawBitmap(_pressedSuperHappy, 10, 10, null);
		else
			canvas.drawBitmap(_superHappy, 10, 10, null);
		if (_selected == _happy)
			canvas.drawBitmap(_pressedHappy, 10, 10, null);
		else
			canvas.drawBitmap(_happy, 30, 80, null);
		if (_selected == _neutral)
			canvas.drawBitmap(_pressedNeutral, 50, 150, null);
		else
			canvas.drawBitmap(_neutral, 50, 150, null);
		if (_selected == _sad)
			canvas.drawBitmap(_pressedSad, 100, 80, null);
		else
			canvas.drawBitmap(_sad, 100, 80, null);
		if (_selected == _crying)
			canvas.drawBitmap(_pressedCrying, 120, 100, null);
		else
			canvas.drawBitmap(_crying, 120, 100, null);
		if (_selected == _angry)
			canvas.drawBitmap(_pressedAngry, 150, 200, null);
		else
			canvas.drawBitmap(_angry, 150, 200, null);
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		float x = event.getX();
		float y = event.getY();
		
		if (eventAction==MotionEvent.ACTION_DOWN) {
			if (x >= 10 && x <= 40 && y >= 10 && y <= 40) { 		//SuperHappy
				_selected = _superHappy;
				return true;
			}
			else if (x >= 10 && x <= 40 && y >= 10 && y <= 40) { 	//Happy
				_selected = _happy;
				return true;
			}
			else if (x >= 10 && x <= 40 && y >= 10 && y <= 40) {	//Neutral
				_selected = _neutral;
				return true;
			}
			else if (x >= 10 && x <= 40 && y >= 10 && y <= 40) {	//Sad
				_selected = _sad;
				return true;
			}
			else if (x >= 10 && x <= 40 && y >= 10 && y <= 40) {	//Crying
				_selected = _crying;
				return true;
			}
			else if (x >= 10 && x <= 40 && y >= 10 && y <= 40) {	//Angry
				_selected = _angry;
				return true;
			}
			else return false;
		}
		
		invalidate();
		return true;
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
}
