package com.example.myfridgehome.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridgehome.R
import com.example.myfridgehome.dto.AddFoodEvent
import com.example.myfridgehome.dto.Food
import kotlinx.android.synthetic.main.edit_food_item_fragment.*

class EditFoodItemEventFragment : Fragment() {

    companion object {
        fun newInstance() =
            EditFoodItemEventFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var food = Food()
    private var _foodId = ""
    private var _foodItems = ArrayList<Food>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_food_item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        viewModel.foodItems.observe(this, Observer { foodItems ->
            inputFormSpinner.setAdapter(
                ArrayAdapter(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    foodItems
                )
            )
        })
        btnSaveFoodItem.setOnClickListener {
            saveEvent()
        }
        btnDeleteFoodItem.setOnClickListener {
            deleteItem()
        }

        actFoodName.setOnItemClickListener { parent, view, position, id ->
            var selectedFood = parent.getItemAtPosition(position) as Food
            _foodId = selectedFood.foodID
        }
        inputFormSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                food = parent?.getItemAtPosition(position) as Food
                actFoodName.setText(food.item)
                actFoodCategory.setText(food.type)
                edtNumber.setText(food.number)
                actUnitOfMeasurement.setText(food.units)
                _foodId = food.foodID
                viewModel.food = food
                viewModel.fetchFoodEvents()
            }

        }
        //connect recycler view
        rcyFoodEvents.hasFixedSize()
        rcyFoodEvents.layoutManager = LinearLayoutManager(context)
        rcyFoodEvents.itemAnimator = DefaultItemAnimator()
        rcyFoodEvents.adapter = EventsAdapter(_foodItems, R.layout.row_layout)

        viewModel.foodItems.observe(this, Observer { events ->
            _foodItems.removeAll(_foodItems)//remove old events
            _foodItems.addAll(events) //add new events
            rcyFoodEvents.adapter!!.notifyDataSetChanged()
        })
    }

    private fun deleteItem() {
        var event = AddFoodEvent()
        with(event) {
            item = actFoodName.text.toString()
            type = actFoodCategory.text.toString()
            number = edtNumber.text.toString()
            units = actUnitOfMeasurement.text.toString()
            foodID = _foodId
        }
        viewModel.deleteFoodItem(event)
        clearAll()
    }

    private fun saveEvent() {
        storeFood()
        var event = AddFoodEvent()
        with(event) {
            item = actFoodName.text.toString()
            type = actFoodCategory.text.toString()
            number = edtNumber.text.toString()
            units = actUnitOfMeasurement.text.toString()
            foodID = _foodId
        }
        viewModel.food.events.add(event)
        viewModel.saveFoodItem(event)
        clearAll()
    }

    private fun storeFood() {
        food.apply {
            item = actFoodName.text.toString()
            type = actFoodCategory.text.toString()
            number = edtNumber.text.toString()
            units = actUnitOfMeasurement.text.toString()
            foodID = _foodId
        }
        viewModel.food = food
    }

    private fun clearAll() {
        actFoodName.setText("")
        actFoodCategory.setText("")
        edtNumber.setText("")
        actUnitOfMeasurement.setText("")
    }

    inner class EventsAdapter(val events: List<Food>, val itemLayout: Int) :
        RecyclerView.Adapter<EditFoodItemEventFragment.EventViewHolder>() {
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

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblEventInfo: TextView = itemView.findViewById(R.id.lblFoodEventInfo)

        fun updateEvent(food: Food) { //this function is called for each item in the collection that should be added to the recyclerview
            lblEventInfo.text = food.toString()
        }
    }
}
