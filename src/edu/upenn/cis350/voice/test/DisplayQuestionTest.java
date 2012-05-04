package edu.upenn.cis350.voice.test;

import edu.upenn.cis350.voice.DisplayQuestion;
import edu.upenn.cis350.voice.R.id;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class DisplayQuestionTest extends ActivityInstrumentationTestCase2<DisplayQuestion> {

	public DisplayQuestionTest() {
		super("edu.upenn.cis350.voice", DisplayQuestion.class);
	}
	
	private DisplayQuestion activity;
	private Button nextButton;
	private Button prevButton;
	
	public void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		nextButton = (Button)activity.findViewById(id.sliderNext);
		prevButton = (Button)activity.findViewById(id.sliderPrev);	
	}
	
	public void testOnBackButtonClick() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				prevButton.performClick();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		
		assertTrue(activity.isFinishing());
		
	}
	
	public void testOnNextButtonClick() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				nextButton.performClick();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		
		assertTrue(activity.isFinishing());
		
	}
	
	public void testSetVisible() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				activity.setVisible();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		
		assertEquals(nextButton.getVisibility(), Button.VISIBLE);
		
	}
	
	public void testSetInvisible() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				activity.setInvisible();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		
		assertEquals(nextButton.getVisibility(), Button.INVISIBLE);
		
	}
	
}
