package com.andre.lood.main.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.andre.lood.main.FoodListing;
import com.andre.lood.main.parser.JsonToFoodTO;
import com.andre.lood.main.to.FoodItemTO;


/**
 * Class responsible for managing the connection to server and
 * for parsing the response into a listview
 *
 */
public class GetFoodSearch extends AsyncTask<String, Void, String> {

	private ProgressDialog progress;
	private Activity context;
	private int response;
	private String userAuth;
	HttpsURLConnection urlConnection;

	public GetFoodSearch(Context context)
	{
		this.context = (Activity) context;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "", "Searching...");
		super.onPreExecute();
	}

	/* 
	 * Here i will dismiss the progressDialog
	 * Disconnect from the server
	 * Parse the result String into an ArrayList of FoodItemTOs 
	 * to add to the List
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		if (progress.isShowing()) {
			progress.dismiss();
		}

		if(urlConnection!= null)
			urlConnection.disconnect();
		
		if(result != null)
		{
			ArrayList<FoodItemTO> foodItems = JsonToFoodTO.parseToFoodItem(result);
			Intent intent = new Intent(context, FoodListing.class);
			intent.putExtra("list", foodItems);
			context.startActivity(intent);
		}

		super.onPostExecute(result);
	}

	@Override
	protected String doInBackground(String... params) {
		try{

			URL url = new URL("https://api.lifesum.com/v1/search/query?type=food&search="+params[0]);
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic a794ecd348a3f71894426c65c37fea35da89a295bcbad687ca68a96fbfc7d371");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setDoOutput(true);
			urlConnection.setReadTimeout(30000);
			urlConnection.setConnectTimeout(30000);
			urlConnection.connect();
			
			//get the server response
			userAuth = streamToString(urlConnection.getInputStream());
			response = urlConnection.getResponseCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(response == 200)
		{
			return userAuth;
		}else{
			return null;
		}	
	}

	/**
	 * Reads the inputstream until end of file is reached
	 * 
	 * @param InputStream from UrlConnection
	 * @return Response body read from server
	 * @throws IOException
	 */
	public static String streamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;

		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		rd.close();
		return sb.toString();
	}

}
