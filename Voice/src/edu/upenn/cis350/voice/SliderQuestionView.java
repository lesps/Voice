package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.content.Context;

public class SliderQuestionView extends ProgressBar {
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
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		
		if (eventAction == MotionEvent.ACTION_DOWN
                || eventAction == MotionEvent.ACTION_MOVE) {
        float x_mouse = event.getX() - padding;
        float width = getWidth() - 2*padding;
        int progress = Math.round((float) getMax() * (x_mouse / width));

        if (progress < 0)
                progress = 0;

        this.setProgress(progress);
        
        if (listener != null)
                listener.onProgressChanged(this, progress);}

		return true;
	}
	
	public int getAnswer(){
		return this.getProgress();
	}

}
