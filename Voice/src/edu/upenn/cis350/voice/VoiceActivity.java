package edu.upenn.cis350.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class VoiceActivity extends Activity {
	public static final String DEBUG = "DEBUG";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onContinueClick(View view){
    	Intent i = new Intent(this, QuestionActivity.class);
    	startActivity(i);
    }
}