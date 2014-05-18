package com.andre.lood.main.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.andre.lood.main.parser.JsonToFoodTO;
import com.andre.lood.main.to.FoodItemTO;
import com.google.gson.Gson;

public class StorageUtils {

	
	/**
	 * Method for saving a FoodItemTO into SharedPreferences
	 * If exists a json will retrieve it, add new entry and save it again
	 * @param context
	 * @param itemTO
	 */
	public static void saveToSharedPreferences(Activity context,FoodItemTO itemTO)
	{
		Editor prefsEditor = getSP(context).edit();
		Gson gson = new Gson();
		String json = "";
		JSONArray jsonArray;

		String savedFood = getSP(context).getString("savedFood", "");

		if(savedFood != null && !savedFood.isEmpty())
		{
			ArrayList<FoodItemTO> savedList = JsonToFoodTO.parseSavedJson(savedFood);
			savedList.add(itemTO);

			json = gson.toJson(savedList);
			prefsEditor.putString("savedFood", json);
		}else{
			json = gson.toJson(itemTO);
			JSONObject reader;
			try {
				reader = new JSONObject(json);
				jsonArray = JsonToFoodTO.savedJson(reader);
				prefsEditor.putString("savedFood", jsonArray.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		prefsEditor.commit();
	}


	public static SharedPreferences getSP(Activity context) {
		SharedPreferences appSharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		return appSharedPrefs;
	}
}
