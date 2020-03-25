package com.example.myfridgehome.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.Recipe

class MainViewModel : ViewModel() {
    private var _recipes: MutableLiveData<ArrayList<Recipe>> = MutableLiveData<ArrayList<Recipe>>()
}
