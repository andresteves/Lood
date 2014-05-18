package com.andre.lood.main.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andre.lood.R;
import com.andre.lood.main.to.FoodItemTO;

public class FoodListAdapter extends ArrayAdapter<FoodItemTO>{

	private final ArrayList<FoodItemTO> foodItems;
	private final Activity context;

	static class ViewHolder
	{
		public TextView name;
		public TextView description;
		public TextView calories;
		public TextView protein;
		public TextView carbohydrates;
		public TextView fat;
		public TextView category;
	}

	public FoodListAdapter(Context context, int textViewResourceId,
			ArrayList<FoodItemTO> objects) {
		super(context, textViewResourceId, objects);

		this.foodItems = objects;
		this.context = (Activity) context;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if(rowView == null)
		{
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.fooditem, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView.findViewById(R.id.foodItem);
			viewHolder.description = (TextView) rowView.findViewById(R.id.description);
			viewHolder.calories = (TextView) rowView.findViewById(R.id.calories);
			viewHolder.category = (TextView) rowView.findViewById(R.id.category);
			viewHolder.fat = (TextView) rowView.findViewById(R.id.fat);
			viewHolder.protein = (TextView) rowView.findViewById(R.id.protein);
			viewHolder.carbohydrates = (TextView) rowView.findViewById(R.id.carbohydrates);
			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();
		FoodItemTO s = foodItems.get(position);
		holder.name.setText(s.getName());
		if(!s.getDesc().isEmpty())
		{
			holder.description.setText("Piece: " + s.getDesc());
		}
		holder.calories.setText("Calories: " + s.getCalories());
		holder.category.setText("Category: " + s.getCategory());
		holder.protein.setText("Protein: " + s.getProtein());
		holder.fat.setText("Fat: " + s.getFat());
		holder.carbohydrates.setText("CH: " + s.getCarboHydrates());

		return rowView;
	}
}
