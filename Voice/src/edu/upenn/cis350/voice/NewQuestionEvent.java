package edu.upenn.cis350.voice;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class NewQuestionEvent extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionpage);
	}
	//The buttons go back to the main welcome screen for now
	public void onBackButtonClick(View view) {
		finish();
	}
	public void onNextButtonClick(View view) {
		finish();
	}
	
}