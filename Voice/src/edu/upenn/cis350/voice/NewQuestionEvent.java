package edu.upenn.cis350.voice;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class NewQuestionEvent extends Activity{
	private VoiceView view;
	private String text;
	private Type type;

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
			view = (VoiceView) findViewById(R.id.sliderQuestionView);
			break;
		case WHEEL:
			setContentView(R.layout.wheel);
			view = (VoiceView) findViewById(R.id.wheelQuestionView);
			break;
		case PICTURE:
			setContentView(R.layout.picture);
			view = (VoiceView) findViewById(R.id.pictureQuestionView);
			break;
		case DRAG:
			setContentView(R.layout.drag);
			view = (VoiceView) findViewById(R.id.dragQuestionView);
			break;
		case BUTTON:
			setContentView(R.layout.button);
			view = (VoiceView) findViewById(R.id.buttonQuestionView);
			break;
		}
	}

	//The buttons go back to the main welcome screen for now
	public void onBackButtonClick(View view) {
		finish();
	}

	public void onNextButtonClick(View view) {
		finish();
	}

}