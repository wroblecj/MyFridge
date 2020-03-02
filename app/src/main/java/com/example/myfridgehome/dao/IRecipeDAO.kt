package com.example.myfridgehome.dao

import com.example.myfridgehome.dto.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRecipeDAO{
//collects ArrayList of Recipe items from JSON file
@GET(value = "assets/storedIngredients.json")
fun getAllRecipes(): Call<ArrayList<Recipe>>

//appends a search query to the URL to allow for filtering autocomplete
@GET(value = "assets/storedIngredients.json")
fun getRecipes(@Query("Combined_Name") countryName: String): Call<ArrayList<Recipe>>

}