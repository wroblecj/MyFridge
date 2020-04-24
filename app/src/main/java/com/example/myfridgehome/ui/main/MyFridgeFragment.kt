package com.example.myfridgehome.ui.main

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridgehome.MainActivity
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
    private var _foodItems = ArrayList<Food>()

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
        json_button_parse.setOnClickListener {
            val foods = getFoodList()
            text_view_result.text = foods.joinToString()
        }
        json_button_write.setOnClickListener {
            val foodsToSave = listOf(Food("celery sticks", "vegetable", "3", "whole"))
            writeFoodsToList(foodsToSave)
            Toast.makeText(
                this.context,
                "Saved ${foodsToSave.size} foods to Fridge.",
                Toast.LENGTH_SHORT
            ).show()
        }

        //end data parse test using preferences manager

        btn_editFoodItems.setOnClickListener {
            (activity as MainActivity).startEditFoodsFragment()
        }
        btn_startSaveFoodEventFragment.setOnClickListener {
            (activity as MainActivity).startAddFoodsFragment()
        }
        //connect recycler view
        recyclerView_Fridge.hasFixedSize()
        recyclerView_Fridge.layoutManager = LinearLayoutManager(context)
        recyclerView_Fridge.itemAnimator = DefaultItemAnimator()
        recyclerView_Fridge.adapter = EventsAdapter(_foodItems, R.layout.row_layout)

        viewModel.foodItems.observe(this, Observer { items ->
            _foodItems.removeAll(_foodItems)//remove old events
            _foodItems.addAll(items) //add new events
            recyclerView_Fridge.adapter!!.notifyDataSetChanged()
        })
    }

    inner class EventsAdapter(val events: List<Food>, val itemLayout: Int) :
        RecyclerView.Adapter<MyFridgeFragment.EventViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return EventViewHolder(view)
        }

        override fun getItemCount(): Int {
            return events.size
        }

        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            val addFood = events.get(position)
            holder.updateEvent(addFood)
        }


    }

    private fun writeFoodsToList(foods: List<Food>) {
        val foodEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit()
        val jsonString = Gson().toJson(foods)
        foodEditor.putString("foods", jsonString).apply()
    }

    private fun getFoodList(): List<Food> {
        val stored_foods = PreferenceManager.getDefaultSharedPreferences(this.context)
        val jsonString = stored_foods.getString("foods", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString, object : TypeToken<List<Food>>() {}.type)
        else
            listOf()
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblEventInfo: TextView = itemView.findViewById(R.id.lblFoodEventInfo)

        fun updateEvent(food: Food) { //this function is called for each item in the collection that should be added to the recyclerview
            lblEventInfo.text = food.toString()
        }
    }

}
