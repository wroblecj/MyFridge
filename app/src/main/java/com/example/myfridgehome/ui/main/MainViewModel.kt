package com.example.myfridgehome.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.Recipe
import com.example.myfridgehome.service.RecipeService

class MainViewModel : ViewModel() {
    private var _recipes: MutableLiveData<ArrayList<Recipe>> = MutableLiveData<ArrayList<Recipe>>()
    var recipeService: RecipeService = RecipeService()

    init {
        fetchRecipes("e")
    }

    fun fetchRecipes(ingredienName: String) {
        _recipes = recipeService.collectRecipes()
    }

    internal var recipes:MutableLiveData<ArrayList<Recipe>>
        get() {return _recipes}
        set(value) {_recipes = value}
}
