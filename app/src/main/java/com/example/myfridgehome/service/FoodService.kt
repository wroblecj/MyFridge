package com.example.myfridgehome.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfridgehome.RetrofitClientInstance
import com.example.myfridgehome.dao.IFoodItemDAO
import com.example.myfridgehome.dto.Food
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG: String = FoodService::class.java.simpleName

class FoodService {

    fun fetchFoods(): MutableLiveData<ArrayList<Food>> {
        Log.d(TAG, "Fetch Started")
        var _countries = MutableLiveData<ArrayList<Food>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IFoodItemDAO::class.java)
        val call = service?.getAllCountries()
        Log.d(TAG, "Before Call")
        call?.enqueue(object : Callback<ArrayList<Food>> {
            override fun onFailure(call: Call<ArrayList<Food>>, t: Throwable) {
                Log.d(TAG, "Call Failed!")

            }

            override fun onResponse(
                call: Call<ArrayList<Food>>,
                response: Response<ArrayList<Food>>
            ) {
                Log.d(TAG, "Inside OnRespond")
                _countries.value = response.body()
            }

        })


        return _countries
    }
}