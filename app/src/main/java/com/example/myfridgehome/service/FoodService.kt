package com.example.myfridgehome.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfridgehome.RetrofitClientInstance
import com.example.myfridgehome.dao.IFoodItemDAO
import com.example.myfridgehome.dto.Recipes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG: String = FoodService::class.java.simpleName

class FoodService {

    fun fetchFoods(): MutableLiveData<ArrayList<Recipes>> {
        Log.d(TAG, "Fetch Started")
        var _countries = MutableLiveData<ArrayList<Recipes>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IFoodItemDAO::class.java)
        val call = service?.getAllRecipes()
        Log.d(TAG, "Before Call")
        call?.enqueue(object : Callback<ArrayList<Recipes>> {
            override fun onFailure(call: Call<ArrayList<Recipes>>, t: Throwable) {
                Log.d(TAG, "Call Failed!")

            }

            override fun onResponse(
                call: Call<ArrayList<Recipes>>,
                response: Response<ArrayList<Recipes>>
            ) {
                Log.d(TAG, "Inside OnRespond")
                _countries.value = response.body()
            }

        })


        return _countries
    }
}