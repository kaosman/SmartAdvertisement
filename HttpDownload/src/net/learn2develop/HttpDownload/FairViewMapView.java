package net.learn2develop.HttpDownload;

import imageloader.ViewFairViewAds;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FairViewMapView extends Activity {

	private final String FAIRVIEW_MAP_URL = "http://www.clearchanneloutdoor.ca/2011/img/malls/fairviewpark/mallmap.png";
	Helper helper = new Helper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fair_view_map_view);

		//Display map of fairview mall on creation
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Bitmap originalbitmap = helper.DownloadImage(FAIRVIEW_MAP_URL);
		Bitmap resizedbitmap = helper.getResizedBitmap(originalbitmap, 571, 449);
		ImageView img = (ImageView) findViewById(R.id.fairview_map);
		img.setImageBitmap(resizedbitmap);
		
		//Create view advertisements button object
		Button button = (Button)findViewById(R.id.fairview_ad_view_button);

		//Button on click listener method
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent( FairViewMapView.this, ViewFairViewAds.class); 
				startActivity(i);
			}
		});
	}
}
