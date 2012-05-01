package edu.upenn.cis350.voice;


import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.util.AttributeSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


public class ProviderInformationActivity extends Activity  {
	String[] return_vals = new String[2];
	
	/** Called when the activity is first created. */
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.providerinfopage);
		}

	
	public String[] getAnswer(){
		return return_vals;
	}
		public void onSubmitButtonClick(View view) {
			EditText locationV = (EditText) findViewById(R.id.location);
			EditText providerV = (EditText) findViewById(R.id.provider);
			
			return_vals[0] = locationV.getText().toString();
			return_vals[1] = providerV.getText().toString();
			
			Intent i = new Intent(ProviderInformationActivity.this, WelcomeActivity.class);

			startActivity(i);
		}

	}