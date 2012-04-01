package edu.upenn.cis350.voice;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class NewQuestionEvent extends Activity{
	private VoiceView userView;
	private TextView tview;
	private String text;
	private Type type;
	
	private Intent i;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		if(b.get("Type") == null)
			throw new IllegalArgumentException();
		if(!(b.get("Type") instanceof Type))
			throw new IllegalArgumentException();
		if(b.get("Text") == null)
			throw new IllegalArgumentException();
		if(!(b.get("Text") instanceof String))
			throw new IllegalArgumentException();

		type = (Type)b.get("Type");
		text = (String)b.get("Text");

		switch(type){

			case SLIDER:
				setContentView(R.layout.slider);
				userView = (VoiceView) findViewById(R.id.sliderQuestionView);
				tview = (TextView) findViewById(R.id.sliderText);
				break;
			case WHEEL:
				setContentView(R.layout.wheel);
				userView = (VoiceView) findViewById(R.id.wheelQuestionView);
				tview = (TextView) findViewById(R.id.wheelText);
				break;
			case PICTURE:
				setContentView(R.layout.picture);
				userView = (VoiceView) findViewById(R.id.pictureQuestionView);
				tview = (TextView) findViewById(R.id.pictureText);
				break;
			case DRAG:
				setContentView(R.layout.drag);
				userView = (VoiceView) findViewById(R.id.dragQuestionView);
				tview = (TextView) findViewById(R.id.dragText);
				break;
			case BUTTON:
				setContentView(R.layout.button);
				userView = (VoiceView) findViewById(R.id.buttonQuestionView);
				tview = (TextView) findViewById(R.id.buttonText);
				break;
		}
		
		tview.setText(text);
	}

	public void onBackButtonClick(View view) {
		i.putExtra("Answer", userView.getAnswer());
		i.putExtra("NextQuestion", true); //Put true if moving forward
		setResult(RESULT_OK, i);
		finish();
	}

	public void onNextButtonClick(View view) {
		i.putExtra("Answer", userView.getAnswer());
		i.putExtra("NextQuestion", false); //Put false if moving backward
		setResult(RESULT_OK, i);
		finish();
	}

}