package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.content.Context;

public class SliderQuestionView extends ProgressBar implements VoiceViewI {
	 private OnProgressChangeListener listener;
	 
     private static int padding = 2;

     public interface OnProgressChangeListener {
             void onProgressChanged(View v, int progress);
     }
	
	public SliderQuestionView(Context c) {
		super(c);
		this.setMax(10);
	}
	
	public SliderQuestionView(Context c, AttributeSet a) {
		super(c, a, android.R.attr.progressBarStyleHorizontal);
		this.setMax(10);
	}
	
	public void setOnProgressChangeListener(OnProgressChangeListener l) {
        listener = l;
        }
	
	/**
	 * Update the slider bar as the user touches it and drags.
	 * 
	 * @param- the motion event from the user
	 * @return- always return true
	 */
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		//While the user is pushing down and/or moving their finger over the slider, and if the touch event is within the padding region
		if (eventAction == MotionEvent.ACTION_DOWN || eventAction == MotionEvent.ACTION_MOVE) {
			float x_mouse = event.getX() - padding;
			float width = getWidth() - 2*padding;
			int progress = Math.round((float) getMax() * (x_mouse / width));

			if (progress < 0){
				progress = 0;
			}

			this.setProgress(progress);
        
			if (listener != null){
				listener.onProgressChanged(this, progress);
				}
			}

		return true;
	}

	/**
	 * Progress max is 10, so a value between 1 and 10 is returned
	 * 
	 * @return- returns the current progress value.
	 */
	public int getAnswer(){
		return this.getProgress();
	}
	
	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}

}
