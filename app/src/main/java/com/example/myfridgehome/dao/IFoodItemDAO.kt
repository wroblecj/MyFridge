package com.example.myfridgehome.dao

import com.example.myfridgehome.dto.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFoodItemDAO {
    //collects ArrayList of Country items from JSON file
    @GET(value = "/api/")
    fun getAllCountries(): Call<ArrayList<Recipes>>

    //appends a search query to the URL to allow for filtering autocomplete
    @GET(value = "/api/")
    fun getPlants(@Query("i") countryName: String): Call<ArrayList<Recipes>>

}