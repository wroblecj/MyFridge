package com.example.myfridgehome.dao

import com.example.myfridgehome.dto.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRecipeDAO{
//collects ArrayList of Recipe items from JSON file
@GET(value = "filter.php")
fun getAllRecipes(): Call<ArrayList<Recipe>>

//appends a search query to the URL to allow for filtering autocomplete
@GET(value = "filter.php")
fun getRecipes(@Query("i") ingredientName: String): Call<ArrayList<Recipe>>

}