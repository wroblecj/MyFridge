package com.example.myfridgehome.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.myfridgehome.R
import com.example.myfridgehome.dto.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.my_fridge_fragment.*

class MyFridgeFragment : Fragment() {

    companion object {
        fun newInstance() = MyFridgeFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_fridge_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //start data parse test using preferences manager
        json_button_parse.setOnClickListener{
            val foods = getFoodList()
            text_view_result.text = foods.joinToString()
        }
        json_button_write.setOnClickListener{
            val foodsToSave = listOf(Food("celery sticks", "vegetable", 3, "whole"))
            writeFoodsToList(foodsToSave)
            Toast.makeText(this.context, "Saved ${foodsToSave.size} foods to Fridge.", Toast.LENGTH_SHORT).show()
        }

        //end data parse test using preferences manager

    }

    private fun writeFoodsToList(foods: List<Food>){
        val foodEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit()
        val jsonString = Gson().toJson(foods)
        foodEditor.putString("foods", jsonString).apply()
    }
    private fun getFoodList(): List<Food>{
        val stored_foods = PreferenceManager.getDefaultSharedPreferences(this.context)
        val jsonString = stored_foods.getString("foods", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString, object: TypeToken<List<Food>>(){}.type)
        else
            listOf()
    }

}
