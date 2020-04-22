package com.example.myfridgehome.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.AddFoodEvent
import com.example.myfridgehome.dto.Food
import com.example.myfridgehome.dto.Recipes
import com.example.myfridgehome.service.FoodService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel : ViewModel() {
    var recipes: MutableLiveData<ArrayList<Recipes>> = MutableLiveData<ArrayList<Recipes>>()
    private var _foodService: FoodService = FoodService()
    private lateinit var firestore: FirebaseFirestore
    private var _food = Food()


    init{
        fetchFoodItems()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun fetchFoodItems() {
        recipes = _foodService.fetchFoods()
    }


    fun saveFoodItem(food: AddFoodEvent) {
        val document= firestore.collection("foodItems").document()
        food.foodID = document.id
        val set =document.set(food)
            set.addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "document failed")
            }
    }
    internal var food: Food
        get() {return _food}
        set(value) {_food = value}

}
