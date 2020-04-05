package com.example.myfridgehome.dto

import com.google.gson.annotations.SerializedName

data class StoredFoods ( @SerializedName ("id") var id : Int, @SerializedName("name")var name: String, @SerializedName("type") var type: String, @SerializedName("quantity") var quantity : Int, @SerializedName("measurment") var measurement : String){
        override fun toString(): String {
        //reformats the data from the JSON file into required String output
        return "$id $name $type $quantity $measurement"
    }

}