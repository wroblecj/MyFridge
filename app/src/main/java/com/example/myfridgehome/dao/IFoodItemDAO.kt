package com.example.myfridgehome.dao

import com.example.myfridgehome.dto.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IFoodItemDAO {
    //collects ArrayList of Country items from JSON file
    @GET(value = "/fridge.json")
    fun getAllRecipes(): Call<ArrayList<Recipes>>
}