package com.example.myfridgehome.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridgehome.R
import com.example.myfridgehome.dto.AddFoodEvent
import kotlinx.android.synthetic.main.add_food_item_fragment.*

class AddFoodItemEventFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddFoodItemEventFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_food_item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let{
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        btnSaveFoodItem.setOnClickListener{
            saveEvent()
        }
        //connect recycler view
        rcyFoodEvents.hasFixedSize()
        rcyFoodEvents.layoutManager = LinearLayoutManager(context)
        rcyFoodEvents.itemAnimator = DefaultItemAnimator()
        rcyFoodEvents.adapter = EventsAdapter(viewModel.food.events, R.layout.row_layout)

    }
    private fun saveEvent() {
        var event = AddFoodEvent()
        with(event){
            item = actFoodName.text.toString()
            type = actFoodCategory.text.toString()
            number = edtNumber.text.toString()
            units = actUnitOfMeasurement.text.toString()

        }
        viewModel.food.events.add(event)
        viewModel.saveFoodItem(event)
        clearAll()
        rcyFoodEvents.adapter?.notifyDataSetChanged()
    }

    private fun clearAll() {
        actFoodName.setText("")
        actFoodCategory.setText("")
        edtNumber.setText("")
        actUnitOfMeasurement.setText("")
    }
    inner class EventsAdapter(val events: List<AddFoodEvent>, val itemLayout : Int) : RecyclerView.Adapter<AddFoodItemEventFragment.EventViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return EventViewHolder(view)
        }

        override fun getItemCount(): Int {
            return events.size
        }

        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            val event = events.get(position)
            holder.updateEvent(event)
        }


    }

    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var lblEventInfo: TextView = itemView.findViewById(R.id.lblFoodEventInfo)

        fun updateEvent(event: AddFoodEvent){ //this function is called for each item in the collection that should be added to the recyclerview
            lblEventInfo.text = event.toString()
        }
    }
}
