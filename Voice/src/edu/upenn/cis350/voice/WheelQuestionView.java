package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

public class WheelQuestionView extends View implements VoiceViewI {

	private Bitmap _spinner;
	private float _degrees = 0;	
	private Matrix _spinMatrix = new Matrix();
	private float initx, inity = 0.0f;
	
	public WheelQuestionView(Context c) {
		super(c);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
	}
	public WheelQuestionView(Context c, AttributeSet a) {
		super(c, a);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(_spinner, _spinMatrix, null);
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		float x = event.getX();
		float y = event.getY();
		
		if (eventAction == MotionEvent.ACTION_DOWN) {
			initx = x;
			inity = y;
		}
		else if (eventAction == MotionEvent.ACTION_MOVE) {
			_degrees = calculateDegrees(x, y, initx, inity);
			_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getHeight()/2);
		}
		else if (eventAction == MotionEvent.ACTION_UP) {
			
		}
		
		invalidate();
		return true;
	}

	private float calculateDegrees(float x1, float y1, float x2, float y2) {
		double offsetX = _spinner.getWidth()/2;
		double offsetY = _spinner.getHeight()/2;
		
		double xa = x1 - offsetX;
		double ya = offsetY - y1;
		double xb = x2 - offsetX;
		double yb = offsetY - y2;
		
		double magA = Math.sqrt(Math.pow(xa, 2.0) + Math.pow(ya, 2.0));
		double magB = Math.sqrt(Math.pow(xb, 2.0) + Math.pow(yb, 2.0));
		
		double unitAX = xa/magA;
		double unitAY = ya/magA;
		double unitBX = xb/magB;
		double unitBY = yb/magB;
		
		double dotProduct = (unitAX*unitBX + unitAY*unitBY);
		
		return (float) Math.acos(dotProduct);
	}
	
	public int getAnswer(){
		return 0;
	}
	
	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}
}
