package net.learn2develop.HttpDownload;

import imageloader.ViewConestogaAds;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ConestogaMapView extends Activity {

	private final String CONESTOGAMALL_MAP_URL = "http://www.crate.ca/showrooms/Conestoga-Mall.jpg";
	Helper helper = new Helper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conestoga_map_view);

		//Display map of conestoga mall on creation
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Bitmap originalbitmap = helper.DownloadImage(CONESTOGAMALL_MAP_URL);
		Bitmap resizedbitmap = helper.getResizedBitmap(originalbitmap, 571, 449);
		ImageView img = (ImageView) findViewById(R.id.conestoga_map);
		img.setImageBitmap(resizedbitmap);
		
		//Create view advertisements button object
		Button button = (Button)findViewById(R.id.conestoga_ad_view_button);

		//Button on click listener method
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent( ConestogaMapView.this, ViewConestogaAds.class); 
				startActivity(i);
			}
		});
	}
}
