package com.example.myfridgehome.dto

import com.google.gson.annotations.SerializedName


data class Food(
    @SerializedName("name") var item: String = "",
    var type: String = "",
    var number: String = "",
    var units: String = "",
    var foodID: String = ""
) {
    private var _events: ArrayList<AddFoodEvent> = ArrayList<AddFoodEvent>()

    var events: ArrayList<AddFoodEvent>
        get() {
            return _events
        }
        set(value) {
            _events = value
        }

    override fun toString() = "$item"

}