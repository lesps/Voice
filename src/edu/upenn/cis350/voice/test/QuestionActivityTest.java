package edu.upenn.cis350.voice.test;

import edu.upenn.cis350.voice.QuestionActivity;
import android.test.ActivityInstrumentationTestCase2;

public class QuestionActivityTest extends ActivityInstrumentationTestCase2<QuestionActivity> {

	public QuestionActivityTest() {
		super("edu.upenn.cis350.voice", QuestionActivity.class);
	}

	private QuestionActivity activity;
	
	public void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}

	public void testSwitchQuestion() {
		int before = activity.getNumQuestion();
		activity.runOnUiThread(new Runnable() {
			public void run() {
				activity.switchQuestion(true);
			}
		});
		getInstrumentation().waitForIdleSync();
		int after = activity.getNumQuestion();
		
		assertTrue(before != after);
		
	}

}
