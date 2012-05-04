package edu.upenn.cis350.voice.db;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import edu.upenn.cis350.voice.WelcomeActivity;
import android.util.Log;

import java.util.List;


public class VoiceCallback extends FindCallback {
	private List<ParseObject> _list;
	private WelcomeActivity _caller;
	
	public VoiceCallback(WelcomeActivity caller){
		_caller = caller;
	}
	
	/**
	 * Called when callback completes; this will update the local database with
	 * the ParseObjects returned by the query.
	 */
	public void done(List<ParseObject> objects, ParseException e){
		if(e==null)
			_caller.updateQuestions(objects);
		else
			e.printStackTrace();
	}
}
