package edu.upenn.cis350.voice;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class VoiceView extends View {
	public VoiceView(Context c){
		super(c);
	}
	
	public VoiceView(Context c, AttributeSet a){
		super(c,a);
	}
	
	public abstract int getAnswer();
}
