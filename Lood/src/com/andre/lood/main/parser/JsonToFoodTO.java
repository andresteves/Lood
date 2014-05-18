package com.andre.lood.main.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.andre.lood.main.to.FoodItemTO;

public class JsonToFoodTO {

	/**
	 * @param String containing the JSON response
	 * @return An ArrayList with the response parsed into FoodItemTOs
	 */
	public static ArrayList<FoodItemTO> parseToFoodItem(String in)
	{
		ArrayList<FoodItemTO> foodItemTOs = new ArrayList<FoodItemTO>();

		try {
			JSONObject reader = new JSONObject(in);
			JSONObject response  = reader.getJSONObject("response");
			JSONArray list =  response.getJSONArray("list");

			int arraySize = list.length();
			for (int i = 0; i < arraySize; i++) {
				JSONObject item = list.getJSONObject(i);
				FoodItemTO foodItemTO = new FoodItemTO();

				foodItemTO.setName(item.getString("title"));
				foodItemTO.setDesc(item.getString("pcstext"));
				foodItemTO.setProtein(item.getDouble("protein"));
				foodItemTO.setCalories(item.getInt("calories"));
				foodItemTO.setCarboHydrates(item.getDouble("carbohydrates"));
				foodItemTO.setFat(item.getDouble("fat"));
				foodItemTO.setCategory(item.getString("category"));

				foodItemTOs.add(foodItemTO);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return foodItemTOs;
	}



	/**
	 * @param String containing the saved JSON
	 * @return An ArrayList with the saved JSON parsed into FoodItemTOs
	 */
	public static ArrayList<FoodItemTO> parseSavedJson(String in)
	{
		ArrayList<FoodItemTO> foodItemTOs = new ArrayList<FoodItemTO>();

		try {
			JSONArray list =  new JSONArray(in);

			int arraySize = list.length();
			for (int i = 0; i < arraySize; i++) {
				JSONObject item = list.getJSONObject(i);
				FoodItemTO foodItemTO = new FoodItemTO();

				foodItemTO.setName(item.getString("name"));
				foodItemTO.setDesc(item.getString("desc"));
				foodItemTO.setProtein(item.getDouble("protein"));
				foodItemTO.setCalories(item.getInt("calories"));
				foodItemTO.setCarboHydrates(item.getDouble("carboHydrates"));
				foodItemTO.setFat(item.getDouble("fat"));
				foodItemTO.setCategory(item.getString("category"));

				foodItemTOs.add(foodItemTO);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return foodItemTOs;
	}

	/**
	 * @param String containing the saved JSON
	 * @return An ArrayList with the saved JSON parsed into FoodItemTOs
	 */
	public static JSONArray savedJson(JSONObject in)
	{	
		JSONArray list =  new JSONArray();
		list.put(in);
		
		return list;
	}
}
