package com.andre.lood.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.andre.lood.R;
import com.andre.lood.main.connection.GetFoodSearch;
import com.andre.lood.main.parser.JsonToFoodTO;
import com.andre.lood.main.to.FoodItemTO;
import com.andre.lood.main.utils.StorageUtils;

public class MainActivity extends Activity {

	EditText textView;
	Button search_b,savedFood_b;
	private static String savedFood;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);

		//Caching HTTP requests to save bandwidth
		setCachingHTTP();

		textView = (EditText) findViewById(R.id.searchForm);
		search_b = (Button) findViewById(R.id.search_b);

		search_b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String searchQuery = textView.getText().toString();
				if(searchQuery != null && !searchQuery.isEmpty())
				{
					new GetFoodSearch(MainActivity.this).execute(searchQuery);
				}
			}
		});

		getSavedFood();
	}

	@Override
	protected void onResume() {
		getSavedFood();
		super.onResume();
	}

	private void getSavedFood()
	{
		//Get saved food if any
		savedFood = StorageUtils.getSP(this).getString("savedFood", "");

		//Shows the button only if there is any food saved...
		if(savedFood != null && !savedFood.isEmpty())
		{
			savedFood_b = (Button) findViewById(R.id.savedFood);
			savedFood_b.setVisibility(View.VISIBLE);
			savedFood_b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
					animation.setDuration(200);
					v.startAnimation(animation);
					animation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {}
						
						@Override
						public void onAnimationRepeat(Animation animation) {}
						
						@Override
						public void onAnimationEnd(Animation animation) {
							ArrayList<FoodItemTO> foodItems = JsonToFoodTO.parseSavedJson(savedFood);
							Intent intent = new Intent(MainActivity.this, FoodListing.class);
							intent.putExtra("list", foodItems);
							startActivity(intent);
						}
					});
					animation = null;
				}
			});
		}
	}

	@Override
	protected void onStop() {
		HttpResponseCache cache = HttpResponseCache.getInstalled();
		if (cache != null) {
			cache.flush();
		}
		super.onStop();
	}

	public void setCachingHTTP()
	{
		try {
			File httpCacheDir = new File(this.getCacheDir(), "http");
			long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
			HttpResponseCache.install(httpCacheDir, httpCacheSize);
		}catch (IOException e) {
			Log.i("LOOD", "HTTP response cache installation failed:" + e);
		}
	}

}
