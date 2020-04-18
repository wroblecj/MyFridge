package com.example.myfridgehome.dto


data class Fridge(var name: String, var category: String, var quantity: String, var measurement: String) {
    override fun toString() = "$category, $name, $quantity $measurement"

}