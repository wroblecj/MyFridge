package com.example.myfridgehome.dto

data class Recipes(var title: String, var ingredients: String) {

    override fun toString(): String {
        //reformats the data from the JSON file into required String output
        return "$title $ingredients"
    }
}