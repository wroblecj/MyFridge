package com.example.myfridgehome.dto

data class Recipe(var title : String = "") {
    override fun toString(): String {
        return title
    }
}