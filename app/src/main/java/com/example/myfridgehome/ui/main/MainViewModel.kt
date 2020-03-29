package com.example.myfridgehome.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.Recipe
import com.example.myfridgehome.service.RecipeService

class MainViewModel : ViewModel() {
    var _recipes: MutableLiveData<ArrayList<Recipe>> = MutableLiveData<ArrayList<Recipe>>()
    var recipeService: RecipeService = RecipeService()

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        _recipes = recipeService.collectRecipes()
    }
}
