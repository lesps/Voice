package edu.upenn.cis350.voice.test;

import java.util.ArrayList;

import edu.upenn.cis350.voice.Question;
import edu.upenn.cis350.voice.WelcomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class WelcomeActivityTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {

	public WelcomeActivityTest() {
		super("edu.upenn.cis350.voice", WelcomeActivity.class);
	}

	private WelcomeActivity activity;
	
	public void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}

	public void testParseAnswer() {
		ArrayList<Integer> intList;
		intList = activity.parseAnswer("1,2,3,4,5");
		
		assertFalse(intList.isEmpty());
		assertEquals(intList.get(0), new Integer(1));
		
	}
	
	public void testConstructQuestion() {
		Question question;
		question = activity.constructQuestion(null);
		
		assertEquals(question, null);
		
	}
	
}
