@file:Suppress("DEPRECATION")

package com.example.myfridgehome.ui.main

import android.os.Bundle

import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import com.example.myfridgehome.R
import kotlinx.android.synthetic.main.activity_search.*

@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.main_fragment, container, false)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = of(this).get(MainViewModel::class.java)
        viewModel._recipes.observe(viewLifecycleOwner, Observer { _recipes ->
            search_bar.setAdapter(
                ArrayAdapter(
                    context!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    _recipes
                )
            )
        })
    }

}
