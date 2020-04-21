package com.example.myfridgehome.dto


data class Fridge(var name: String ="", var category: String ="", var quantity: Int =0, var measurement: String ="") {
    private var _events: ArrayList<AddFoodEvent> = ArrayList<AddFoodEvent>()

    var events: ArrayList<AddFoodEvent>
        get(){return _events}
        set(value) {_events = value}

    override fun toString() = "$category, $name, $quantity $measurement"

}