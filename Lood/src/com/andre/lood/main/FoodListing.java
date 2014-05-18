package com.andre.lood.main;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.andre.lood.R;
import com.andre.lood.main.adapter.FoodListAdapter;
import com.andre.lood.main.to.FoodItemTO;
import com.andre.lood.main.utils.StorageUtils;

public class FoodListing extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foodlisting);

		listView = (ListView) findViewById(R.id.foodList);

		@SuppressWarnings("unchecked")
		final ArrayList<FoodItemTO> foodItems = (ArrayList<FoodItemTO>) getIntent().getSerializableExtra("list");

		listView.setAdapter(new FoodListAdapter(this, R.layout.fooditem, foodItems));

		//Set action for saving the food
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				//Animation of fade in when long press item
				Animation animation = AnimationUtils.loadAnimation(FoodListing.this, R.anim.fadein);
				animation.setDuration(500);
				view.startAnimation(animation);
				animation = null;
				
				//Dialog to request action from user
				AlertDialog dialog = new AlertDialog.Builder(FoodListing.this).create();
				dialog.setTitle("Save");
				dialog.setMessage("Wish to save it?");
				dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						StorageUtils.saveToSharedPreferences(FoodListing.this, foodItems.get(position));
						dialog.dismiss();
					}
				});
				
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				dialog.show();
				
				return false;
			}
		});
	}
}
