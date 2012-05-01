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
	private float _initx, _inity = 0.0f;
	private boolean isAnimate;
	private DisplayQuestion _parent;
	
	public WheelQuestionView(Context c) {
		super(c);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
		isAnimate = false;
	}
	public WheelQuestionView(Context c, AttributeSet a) {
		super(c, a);
		_spinner = BitmapFactory.decodeResource(getResources(), R.drawable.spinner);
		isAnimate = false;
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(_spinner, _spinMatrix, null);
		if(isAnimate){
			float end = _degrees+ 60;
			setAnswer(getAnswer());
			for(float rotate = _degrees; rotate< end; rotate++){
				_spinMatrix.preRotate(rotate, _spinner.getWidth()/2, _spinner.getWidth()/2);
				invalidate();
			}
		}
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		float x = event.getX();
		float y = event.getY();
		
		if (eventAction == MotionEvent.ACTION_DOWN) {
			_initx = x;
			_inity = y;
		}
		else if (eventAction == MotionEvent.ACTION_MOVE) {
			float degrees = calculateDegrees(x, y, _initx, _inity);
			_degrees += degrees;
			_degrees %= 360;
			_spinMatrix.postRotate(degrees, _spinner.getWidth()/2, _spinner.getHeight()/2);
			x = event.getX();
			y = event.getY();
			_initx = x;
			_inity = y;
		}
		else if (eventAction == MotionEvent.ACTION_UP) {
			System.out.println(_degrees);
		}

		_parent.setVisible();
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
		
		double angleRads = Math.atan2(yb, xb) - Math.atan2(ya, xa);
		
		return (float) ((float) angleRads*(180/Math.PI));		
	}
	
	public int getAnswer(){
		if (_degrees < 0)
			_degrees += 360;
		
		if (_degrees >= 0 && _degrees < 60)
			return 10;
		else if (_degrees >= 60 && _degrees < 120)
			return 6;
		else if (_degrees >= 120 && _degrees < 180)
			return 2;
		else if (_degrees >= 180 && _degrees < 240)
			return 4;
		else if (_degrees >= 240 && _degrees < 300)
			return 0;
		else if (_degrees >= 300 && _degrees < 360)
			return 8;
		else
			return -1;
	}
	
	public void setAnswer(int prevAnswer){
		switch (prevAnswer) {
			case -1:
				return;
			case 10:
				_degrees = 30;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			case 8:
				_degrees = 330;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			case 6:
				_degrees = 90;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			case 4:
				_degrees = 210;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			case 2:
				_degrees = 150;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			case 0:
				_degrees = 270;
				_spinMatrix.setRotate(_degrees, _spinner.getWidth()/2, _spinner.getWidth()/2);
				return;
			default:
				return;
		}
	}
	
	public void animate(){
		isAnimate = true;
		invalidate();
	}
	
	public void setParent(DisplayQuestion d){
		_parent = d;
	}
}
