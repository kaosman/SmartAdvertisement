package imageloader;

import imageloader.Utils;
import simulation.*;
import java.util.List;
import net.learn2develop.HttpDownload.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ViewConestogaAds extends Activity implements OnItemClickListener {

	/** Dropbox links to advertisements embedded in XML files */
	private static final String clothes_ad_link= "https://www.dropbox.com/s/aimbcpwcnir7yjo/clothes_ads.xml?dl=1";
	private static final String technology_ad_link="https://www.dropbox.com/s/x3ii72z3rasx26k/technology_ads.xml?dl=1";
	private static final String jewellery_ad_link="https://www.dropbox.com/s/f46qryuytg62lwo/jewellery_ads.xml?dl=1";
	private static final String food_ad_link="https://www.dropbox.com/s/ry2c0hpdhxl8pp6/food_ads.xml?dl=1";
	private static final String shoes_ad_link="https://www.dropbox.com/s/avb7psu3lp1ehxn/shoes_ads.xml?dl=1";

	/**
	 * Category ID variables. Each ID corresponds to a specific category. Needed
	 * to re direct user to a specific set of ads based on wifi router input
	 */
	private static final int CLOTHES = 0;
	private static final int TECHNOLOGY = 1;
	private static final int JEWELLERY = 2;
	private static final int FOOD = 3;
	private static final int SHOES = 4;
	
	int category = -1;

	List<Item> arrayOfList;
	ListView listView;
	
	/** Object simulation */
	Simulate simulate = new Simulate();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_conestoga_ads);

		listView = (ListView) findViewById(R.id.listview_con);
		listView.setOnItemClickListener(this);

		//Call simulation function that returns a single int value
		 category  = simulate.simulation();
		
		 /**Switch the value of category which is the simulation output */
		switch (category) {

		case 0 : if(Utils.isNetworkAvailable(ViewConestogaAds.this)) {
			new MyTask().execute(clothes_ad_link);
		}else {
			showToast("No Network Connection!!!");
		}
		break;

		case 1 : if(Utils.isNetworkAvailable(ViewConestogaAds.this)) {
			new MyTask().execute(technology_ad_link);
		}else {
			showToast("No Network Connection!!!");
		}
		break;

		case 2 : if(Utils.isNetworkAvailable(ViewConestogaAds.this)) {
			new MyTask().execute(jewellery_ad_link);
		}else {
			showToast("No Network Connection!!!");
		}
		break;

		case 3 : if(Utils.isNetworkAvailable(ViewConestogaAds.this)) {
			new MyTask().execute(food_ad_link);
		}else {
			showToast("No Network Connection!!!");
		}
		break;

		case 4 : if(Utils.isNetworkAvailable(ViewConestogaAds.this)) {
			new MyTask().execute(shoes_ad_link);
		}else {
			showToast("No Network Connection!!!");
		}
		break;
		
		case -1 : showToast("Please click on ads button");
		}
	}

	// My AsyncTask start...

	class MyTask extends AsyncTask<String, Void, Void> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(ViewConestogaAds.this);
			pDialog.setMessage("Loading...");
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... params) {
			arrayOfList = new NamesParser().getData(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == arrayOfList || arrayOfList.size() == 0) {
				showToast("No data found from web!!!");
				ViewConestogaAds.this.finish();
			} else {

				// check data...
				/*
				 * for (int i = 0; i < arrayOfList.size(); i++) { Item item =
				 * arrayOfList.get(i); System.out.println(item.getId());
				 * System.out.println(item.getTitle());
				 * System.out.println(item.getDesc());
				 * System.out.println(item.getPubdate());
				 * System.out.println(item.getLink()); }
				 */

				setAdapterToListview();

			}

		}
	}
	//java.lang.NoClassDefFoundError: com.nostra13.universalimageloader.core.DisplayImageOptions$Builder
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Item item = arrayOfList.get(position);
		Intent intent = new Intent(ViewConestogaAds.this, DetailActivity.class);
		intent.putExtra("url", item.getLink());
		intent.putExtra("title", item.getTitle());
		intent.putExtra("desc", item.getDesc());
		startActivity(intent);
	}

	public void setAdapterToListview() {
		NewsRowAdapter objAdapter = new NewsRowAdapter(ViewConestogaAds.this,
				R.layout.row, arrayOfList);
		listView.setAdapter(objAdapter);
	}

	public void showToast(String msg) {

	}
}
