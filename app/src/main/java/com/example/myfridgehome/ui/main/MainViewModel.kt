package com.example.myfridgehome.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.Food
import com.example.myfridgehome.service.FoodService

class MainViewModel : ViewModel() {
    var _foods: MutableLiveData<ArrayList<Food>> = MutableLiveData<ArrayList<Food>>()
    var foodService: FoodService = FoodService()

    init{
        fetchFoodItems()
    }

    fun fetchFoodItems() {
        _foods = foodService.fetchFoods()
    }


}
