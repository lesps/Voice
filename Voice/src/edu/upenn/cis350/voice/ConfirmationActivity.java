package edu.upenn.cis350.voice;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmationActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmation);
	}
	
	public void onBackButtonClick(View view){
		Intent i = new Intent();
		i.putExtra("Submit", false);
		setResult(RESULT_OK, i);
		finish();
	}
	
	public void onSubmitButtonClick(View view){
		Intent i = new Intent();
		i.putExtra("Submit", true);
		setResult(RESULT_OK, i);
		finish();
	}
}
