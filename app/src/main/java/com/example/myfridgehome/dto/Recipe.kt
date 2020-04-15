package com.example.myfridgehome.dto

data class Recipe(var title : String = "", var mainIngredient: String = "") {
    override fun toString(): String {
        return title
    }
}