package net.learn2develop.HttpDownload;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

public class HttpDownload extends Activity {

/*****Mall map URLs**********/	
	private final String CONESTOGAMALL_MAP_URL = "http://www.crate.ca/showrooms/Conestoga-Mall.jpg";
	private final String FAIRVIEW_MAP_URL = "http://www.clearchanneloutdoor.ca/2011/img/malls/fairviewpark/mallmap.png";

/*****Clothes Ad URLs********/	
	private final String HOLLISTER_AD_URL="http://smartcanucks.ca/wp-content/uploads/2011/01/hollister_canada11.jpg";
	private final String GAP_AD_URL="http://www.shoppingnsales.com/wp-content/uploads/2011/12/20111212-The-Gap-Sale.jpg";
	private final String ABERCROMBIE_AD_URL="http://1.bp.blogspot.com/_-IEJya13dsA/TCXgMgHv24I/AAAAAAAAAp0/b_AEYlg4Q-M/s1600/abercrombie-fitch-discount.png";

/*****Technology Ad URLs********/	
	private final String BESTBUY_AD_URL="http://dam-img.rfdcontent.com/cms/162/787/920x2000_smart_fit.jpg";
	private final String SOURCE_AD_URL="http://dam-img.rfdcontent.com/cms/099/966/920x2000_smart_fit.jpg";

/*****Food Ad URLs***********/
	private final String MCDONALDS_AD_URL="http://www.everyday.com.my/photo/2009-February-Mcdonald-s-Greatest-Saving-Coupon.jpg";
	private final String TACOBELL_AD_URL="http://couponfish.net/ca/wp-content/uploads/2012/02/Untitled.png";
	
/*****Shoes Ad URLs**********/
	private final String BROWNS_AD_URL="http://www.bargainmoose.ca/wp-content/uploads/2012/08/browns.jpg";
	private final String STEVEMADDEN_AD_URL="http://www.flyercenter.com/flyer_images/coupon/1353690467359.jpg";
	
/*******Jewelry Ad URLs*********/
	private final String JEWELERY_AD_URL="http://sg.shoppingnsales.com/wp-content/uploads/2010/06/20100611-SK-Jewellery-Sale.jpg";

	int category=0;
	//cateogry = categorychosen();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_download);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		//If category == 0
		if(category == 0 ){
			Bitmap originalbitmap = DownloadImage(HOLLISTER_AD_URL);
			Bitmap resizedbitmap = getResizedBitmap(originalbitmap, 571, 449);
			ImageView img = (ImageView) findViewById(R.id.imageView1);
			img.setImageBitmap(resizedbitmap);
			
			
		}
		
//		Bitmap originalbitmap = DownloadImage(MCDONALDS_AD_URL);
//		Bitmap resizedbitmap = getResizedBitmap(originalbitmap, 571, 449);
//		ImageView img = (ImageView) findViewById(R.id.imageView1);
//		img.setImageBitmap(resizedbitmap);
	}

/******************************************************************************/
	private InputStream OpenHttpConnection(String urlString) throws IOException
	{
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString); 
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))                     
			throw new IOException("Not an HTTP connection");

		try{
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect(); 

			response = httpConn.getResponseCode();                 
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();                                 
			}                     
		}
		catch (Exception ex)
		{
			throw new IOException("Error connecting");            
		}
		return in;     
	}
	
/******************************************************************************/
//Method to download image from URL
	private Bitmap DownloadImage(String URL)
	{        
		Bitmap bitmap = null;
		InputStream in = null;        
		try {
			in = OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bitmap;                
	}

/******************************************************************************/
//Method to resize bitmap obtained from URL
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}
}
