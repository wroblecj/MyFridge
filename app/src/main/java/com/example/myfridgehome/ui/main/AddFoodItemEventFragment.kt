package com.example.myfridgehome.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }
    private fun saveEvent() {
        var event = AddFoodEvent()
        with(event){
            item = actFoodName.text.toString()
            type = actFoodCategory.text.toString()
            number = edtNumber.text.toString()
            units = actUnitOfMeasurement.text.toString()
        }
    }

}
