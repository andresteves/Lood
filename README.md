Lood
====

Looking for food


Use a simple UI from Android which included: 2 TextViews ("Title" and "Description"), 1 EditText and 2 Buttons ("Search" and "Saved Food");

For search created an AsyncClass to process the request and a JsonParser class to parse the response into an Object called FoodItemTO who included the food name, pctext, calories, carbohydrates, fat, protein and category.

The app was two Activities, one for presenting the search screen and another for listing the results.

The user can save an item by long pressing it and it will be saved to Android SharedPreferences as a JSON string.
