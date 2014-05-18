package com.andre.lood.main.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FoodItemTO implements Serializable{

	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;


	private String name = "";
	
	private String desc = "";
	
	private double carboHydrates = 0.0;
	
	private double protein = 0.0;
	
	private double fat = 0.0;
	
	private int calories = 0;
	
	private String category = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getCarboHydrates() {
		return carboHydrates;
	}

	public void setCarboHydrates(double carboHydrates) {
		this.carboHydrates = carboHydrates;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
