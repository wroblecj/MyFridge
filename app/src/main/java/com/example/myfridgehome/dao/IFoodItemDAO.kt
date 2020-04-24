package com.example.myfridgehome.dao

import com.example.myfridgehome.dto.Food
import retrofit2.Call
import retrofit2.http.GET

interface IFoodItemDAO {
    //collects ArrayList of Country items from JSON file
    @GET(value = "/fridge.json")
    fun listAllFoodSuggestions(): Call<ArrayList<Food>>
}