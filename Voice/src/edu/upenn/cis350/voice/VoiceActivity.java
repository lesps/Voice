package edu.upenn.cis350.voice;

    import edu.upenn.cis350.voice.db.DBManager;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.content.Intent;

    public class VoiceActivity extends Activity {
        private DBManager dataManager;

    	/** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            //Creates an instance of the Data Access Object
            dataManager= new DBManager(this);
            dataManager.open();
            
        }
        public void onContinueClick(View view){
    	//Create and intent using the new activity and the class to be created (A quiz activity)
    	Intent i = new Intent(VoiceActivity.this, QuestionActivity.class);
    	
    	startActivity(i);
    
    }

    }