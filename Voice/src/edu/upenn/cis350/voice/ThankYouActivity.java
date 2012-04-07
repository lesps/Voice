package edu.upenn.cis350.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThankYouActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thankyou);
	}

	public void onFinishButtonClick(View view){
		Intent i = new Intent(this, WelcomeActivity.class);
		startActivity(i);
	}
}
