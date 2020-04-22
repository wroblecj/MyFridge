package com.example.myfridgehome.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridgehome.R
import com.example.myfridgehome.dto.AddFoodEvent
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.recipes.observe(viewLifecycleOwner, Observer { _food_items ->
            actFoodSearch.setAdapter(
                ArrayAdapter(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    _food_items
                )
            )
        })
        BtnMyFridge.setOnClickListener {
            startFridgeFragment()
        }
        BtnRecipes.setOnClickListener{
            startRecipeFragment()
        }
        BtnFvtRecipes.setOnClickListener {
            startFavoriteFragment()
        }
        BtnGrocery.setOnClickListener{
            startGroceryFragment()
        }
    }

    private fun startFridgeFragment() {
        TODO("Not yet implemented")
    }

    private fun startRecipeFragment() {
        TODO("Not yet implemented")
    }

    private fun startFavoriteFragment() {
        TODO("Not yet implemented")
    }

    private fun startGroceryFragment() {
        TODO("Not yet implemented")
    }



}
