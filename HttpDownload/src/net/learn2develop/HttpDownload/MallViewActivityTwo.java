package net.learn2develop.HttpDownload;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MallViewActivityTwo extends Activity {

	private RadioGroup mallGroup;
	private RadioButton mallRadioButton;
	private Button btnDisplay;
	private final String MALL_NAMES[] = {"Conestoga Mall","Fairview Mall","Uptown Waterloo"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mall_view_activity_two);

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		mallGroup = (RadioGroup) findViewById(R.id.mallGroup);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);

		btnDisplay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// get selected radio button from radioGroup
				int index = mallGroup.indexOfChild(findViewById(mallGroup.getCheckedRadioButtonId()));

				switch (index) {

				//If selectedId = 0. Mall is Conestoga mall. Redirect user to conestoga mall map.
				case 0 : Toast.makeText(getApplicationContext(), MALL_NAMES[0], Toast.LENGTH_SHORT).show();
				Intent i1 = new Intent( MallViewActivityTwo.this, ConestogaMapView.class); 
				startActivity(i1);
				break;
				
				//If selectedId = 1. Mall is Fairview mall. Redirect user to Fairvew mall map.
				case 1 : Toast.makeText(getApplicationContext(), MALL_NAMES[1], Toast.LENGTH_SHORT).show();
				Intent i2 = new Intent( MallViewActivityTwo.this, FairViewMapView.class); 
				startActivity(i2);
				break;
				
				//If selectedId = 2. Mall is Uptown mall. Redirect user to Uptown mall map.
				case 2 : Toast.makeText(getApplicationContext(), MALL_NAMES[2], Toast.LENGTH_SHORT).show();
				Intent i3 = new Intent( MallViewActivityTwo.this, UptownMapView.class); 
				startActivity(i3);
				break;
				}
			}
		});
	}
}
