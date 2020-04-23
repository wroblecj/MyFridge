package com.example.myfridgehome.dto

data class Recipes(var name: String, var recipeID: String) {

    override fun toString(): String {
        //reformats the data from the JSON file into required String output
        return "$name"
    }
}