package com.example.myfridgehome.dto

data class AddFoodEvent(
    var item: String = "",
    var type: String = "",
    var number: String = "",
    var units: String = "",
    var foodID: String = ""
) {
    override fun toString(): String {
        return "$item $type $number $units"
    }
}