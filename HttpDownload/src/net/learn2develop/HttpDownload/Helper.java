package net.learn2develop.HttpDownload;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Helper {
	
	Helper (){
		
	}
	
	/**Method to establish http connection
	 * @param String URL
	 * @return inputstream
	 */	
	public InputStream OpenHttpConnection(String urlString) throws IOException
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

	/**Method to download image obtained from URL as bitmap
	 * @param String URL
	 * @return downloaded bitmap
	 */	
	public Bitmap DownloadImage(String URL)
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

	/**Method to resize bitmap obtained from URL
	 * @param bitmap image
	 * @param int newHeight
	 * @param int newWidth
	 * @return resized bitmap
	 */
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
