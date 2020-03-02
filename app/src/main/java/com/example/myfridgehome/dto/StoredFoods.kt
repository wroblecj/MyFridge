package com.example.myfridgehome.dto

data class StoredFoods(var id : Int, var name: String, var type: String, var quantity : Int, var measurement : String){
        override fun toString(): String {
        //reformats the data from the JSON file into required String output
        return "$id $name $type $quantity $measurement"
    }

}