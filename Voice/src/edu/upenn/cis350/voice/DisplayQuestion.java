package edu.upenn.cis350.voice;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DisplayQuestion extends Activity{
	private VoiceViewI userView;
	private TextView tview;
	private Button nextButton;
	private Button prevButton;
	private String text;
	private Type type;
	private Integer answer;
	private Integer number;

	/**
	 * Initialize the question to be displayed
	 */
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
		answer = (Integer)b.get("Answer");
		number = (Integer)b.get("Number");

		switch(type){
			case SLIDER:
				setContentView(R.layout.slider);
				userView = (VoiceViewI) findViewById(R.id.sliderQuestionView);
				tview = (TextView) findViewById(R.id.sliderText);
				nextButton = (Button) findViewById(R.id.sliderNext);
				prevButton = (Button) findViewById(R.id.sliderPrev);
				break;
			case WHEEL:
				setContentView(R.layout.wheel);
				userView = (VoiceViewI) findViewById(R.id.wheelQuestionView);
				tview = (TextView) findViewById(R.id.wheelText);
				nextButton = (Button) findViewById(R.id.wheelNext);
				prevButton = (Button) findViewById(R.id.wheelPrev);
				break;
			case DRAG:
				setContentView(R.layout.drag);
				userView = (VoiceViewI) findViewById(R.id.dragQuestionView);
				tview = (TextView) findViewById(R.id.dragText);
				nextButton = (Button) findViewById(R.id.dragNext);
				prevButton = (Button) findViewById(R.id.dragPrev);
				break;
			case BUTTON:
				setContentView(R.layout.button);
				userView = (VoiceViewI) findViewById(R.id.buttonQuestionView);
				tview = (TextView) findViewById(R.id.buttonText);
				nextButton = (Button) findViewById(R.id.buttonNext);
				prevButton = (Button) findViewById(R.id.buttonPrev);
				break;
		}
		
		tview.setText(text);
		userView.setParent(this);  //Set reference to parent object to be caller
		userView.setAnswer(answer);
		if(answer < 0)
			nextButton.setVisibility(View.GONE);
		if(number==0)
			prevButton.setVisibility(View.GONE);
	}

	/**
	 * Handle the back button press
	 * @param view
	 */
	public void onBackButtonClick(View view) {
		Intent i = new Intent();
		i.putExtra("Answer", userView.getAnswer());
		i.putExtra("NextQuestion", false); //Put true if moving forward
		setResult(RESULT_OK, i);
		finish();
	}
	
	@Override
	public void onBackPressed(){
		Intent i = new Intent();
		i.putExtra("Answer", userView.getAnswer());
		i.putExtra("NextQuestion", false); //Put true if moving forward
		setResult(RESULT_OK, i);
		finish();
	}

	/**
	 * Handle the next button press
	 * @param view
	 */
	public void onNextButtonClick(View view) {
		Intent i = new Intent();
		i.putExtra("Answer", userView.getAnswer());
		i.putExtra("NextQuestion", true); //Put false if moving backward
		
		userView.animate();
		
		setResult(RESULT_OK, i);
		finish();
	}
	
	public void setVisible(){
		nextButton.setVisibility(View.VISIBLE);
	}
	
	public void setInvisible(){
		nextButton.setVisibility(View.GONE);
	}

}