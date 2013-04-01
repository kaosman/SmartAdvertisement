/**
 * Activity to display a list of malls and redirect user to the selected mall's map 
 */

package net.learn2develop.HttpDownload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MallViewActivity extends Activity {

	private final String MALL_NAMES[] = {"Conestoga Mall","Fairview Mall","Uptown Waterloo"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Set content view to mall view on selection
		setContentView(R.layout.activity_mall_view);
		
		//Create spinner object
		final Spinner spinner = (Spinner)findViewById(R.id.mall_spinner);

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, MALL_NAMES);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		//On selecting a spinner option (mall name) direct view to mall map display activity
		 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
	                
	                int index = arg0.getSelectedItemPosition();
	                
	                //If index = 0. Mall is Conestoga mall. Redirect user to conestoga mall map.
	                if(index == 0){
	                	Toast.makeText(getApplicationContext(), MALL_NAMES[0], Toast.LENGTH_SHORT).show();
	                	Intent i = new Intent( MallViewActivity.this, ConestogaMapView.class); 
	                	startActivity(i);
	                }
	                
	                //If index = 1. Mall is Conestoga mall. Redirect user to fairview mall map.
	                if(index == 1){
	                	Toast.makeText(getApplicationContext(), MALL_NAMES[1], Toast.LENGTH_SHORT).show();
	                	Intent i = new Intent( MallViewActivity.this, FairViewMapView.class); 
	                	startActivity(i);
	                }
	                
	                //If index = 2. Mall is Conestoga mall. Redirect user to uptown waterloo mall map.
	                if(index == 2){
	                	Toast.makeText(getApplicationContext(), MALL_NAMES[2], Toast.LENGTH_SHORT).show();
	                	Intent i = new Intent( MallViewActivity.this, UptownMapView.class); 
	                	startActivity(i);
	                }
	                
	            }

	            public void onNothingSelected(AdapterView<?> parent) {
	                // do nothing
	                
	            }
	                      
	        });
	}
}
