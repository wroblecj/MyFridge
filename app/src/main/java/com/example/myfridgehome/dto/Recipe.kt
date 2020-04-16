package com.example.myfridgehome.dto

import com.google.gson.annotations.SerializedName

data class Recipe(var idMeal : Int = 12345, @SerializedName("strMeal") var mainIngredient: String = "") {
    override fun toString(): String {
        return mainIngredient
    }
}