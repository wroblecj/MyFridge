package com.example.myfridgehome.service

import androidx.lifecycle.MutableLiveData
import com.example.myfridgehome.RetrofitClientInstance
import com.example.myfridgehome.dao.IRecipeDAO
import com.example.myfridgehome.dto.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeService {
    fun collectRecipes(): MutableLiveData<ArrayList<Recipe>> {
        var _recipes = MutableLiveData<ArrayList<Recipe>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IRecipeDAO::class.java)
        val call = service?.getAllRecipes()
        call?.enqueue(object : Callback<ArrayList<Recipe>> {
            override fun onFailure(call: Call<ArrayList<Recipe>>, t: Throwable) {
                val j = 1 + 1
                val i = 1 + 1
            }

            override fun onResponse(
                call: Call<ArrayList<Recipe>>,
                response: Response<ArrayList<Recipe>>
            ) {
                _recipes.value = response.body()
            }

        })


        return _recipes
    }
}