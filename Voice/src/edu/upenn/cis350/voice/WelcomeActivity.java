package edu.upenn.cis350.voice;

    import edu.upenn.cis350.voice.db.DBManager;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.content.Intent;
    import com.parse.Parse;
    import com.parse.ParseObject;
    
    public class WelcomeActivity extends Activity {

    	/** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Parse.initialize(this, "pPpqQgvXPcLhyGUNTD7ktBhXEsMWVybkyq89kamw",
            		"5GMEKejNwtcqJBSO6G4gcvjbD2mC6dgMi3XdgnQY");
            setContentView(R.layout.main);
            
        }
        public void onContinueClick(View view){
    	//Create and intent using the new activity and the class to be created (A quiz activity)
    	Intent i = new Intent(WelcomeActivity.this, QuestionActivity.class);
    	
    	startActivity(i);
    
    }

    }