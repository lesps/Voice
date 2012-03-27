package edu.upenn.cis350.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VoiceActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onContinueclick(View view){
    	Intent i = new Intent(this, QuestionActivity.class);
    }
}