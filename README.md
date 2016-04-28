Lood
====

Looking for food - Created for a specific person, please use as you like.


Use a simple UI from Android which included: 2 TextViews ("Title" and "Description"), 1 EditText and 2 Buttons ("Search" and "Saved Food");

For search created an AsyncClass to process the request and a JsonParser class to parse the response into an Object called FoodItemTO who included the food name, pctext, calories, carbohydrates, fat, protein and category.

The app was two Activities, one for presenting the search screen and another for listing the results.

The user can save an item by long pressing it and it will be saved to Android SharedPreferences as a JSON string.


License
=======

Lood is available under the MIT license.

Copyright © 2015 Andre Esteves.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
