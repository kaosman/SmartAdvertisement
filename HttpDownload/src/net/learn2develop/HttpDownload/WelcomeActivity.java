/**
 * Activity to welcome user
*/



package net.learn2develop.HttpDownload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends Activity {

	@Override
	//Method executed when activity is first created. Akin to main method of class.
	//@param saved instance state
	//@return void
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome); //sets content view to that of layout 'welcome'

		//Create button object
		Button button = (Button)findViewById(R.id.welcome_button);
		
		//Button on click listener method
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent( WelcomeActivity.this, MallViewActivityTwo.class); 
				startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	


}
