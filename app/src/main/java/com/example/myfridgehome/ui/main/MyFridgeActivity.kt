package com.example.myfridgehome.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.example.myfridgehome.R
import kotlinx.android.synthetic.main.activity_my_fridge.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.myfridgehome.dto.Fridge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class MyFridgeActivity : AppCompatActivity() {
    private lateinit var mTextViewResult: TextView
    private lateinit var mQueue : RequestQueue
    private lateinit var addFoodFragment: AddFoodItemEventFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fridge)

        addFoodFragment = AddFoodItemEventFragment.newInstance()

        //start newest data parse test using recyclerview



        //end newest data parse test using recyclerview

        //start data parse test using preferences manager
        json_button_parse.setOnClickListener{
            val foods = getFoodList()
            text_view_result.text = foods.joinToString()
        }
        json_button_write.setOnClickListener{
            val foodsToSave = listOf(Fridge("celery sticks", "vegetable", "3", "whole"))
            writeFoodsToList(foodsToSave)
            Toast.makeText(this,"Saved ${foodsToSave.size} foods to Fridge.", Toast.LENGTH_SHORT).show()
        }
        //end data parse test using preferences manager
        btn_startSaveFoodEventFragment.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, addFoodFragment)
                .commitNow()
            activeFragment = addFoodFragment
        }
        //start data parse attempt using http - currently failing
/*        mTextViewResult = findViewById(R.id.text_view_result)
        json_button_parse.setOnClickListener{
            parseAction()
        }
        mQueue = Volley.newRequestQueue(this)*/
        //end data parse attempt using http - currently failing
    }

    private fun writeFoodsToList(foods: List<Fridge>){
        val foodEditor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(foods)
        foodEditor.putString("foods", jsonString).apply()
    }
    private fun getFoodList(): List<Fridge>{
        val stored_foods = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = stored_foods.getString("foods", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString, object: TypeToken<List<Fridge>>(){}.type)
        else
            listOf()
    }

    fun parseAction() {
        var url : String ="https://vibrantartgroup.com/fridge.json"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                fun onResponse(response: JSONObject) {
                    val jsonArray: JSONArray = response.getJSONArray("foodItem")
                    for(i in 0 until jsonArray.length()){
                        val food_item: JSONObject = jsonArray.getJSONObject(i)
                        val cat : String = food_item.getString("category")
                        val quant : String = food_item.getString("quantity")
                        val measure : String = food_item.getString("measurement")

                        mTextViewResult.append(cat + ", " + quant + ", " + measure + "\n\n")
                    }
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        )
        mQueue.add(request)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.my_fridge_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ActionBtnRecipes -> {
                val recipeActionIntent = Intent(this, RecipeActivity::class.java)
                startActivity(recipeActionIntent)
                return true
            }
            R.id.ActionBtnFavorites -> {
                val favoritesActionIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(favoritesActionIntent)
                return true
            }
            R.id.ActionBtnMyFridge -> {
                val fridgeActionIntent = Intent(this, MyFridgeActivity::class.java)
                startActivity(fridgeActionIntent)
                return true
            }
            R.id.ActionBtnGrocery -> {
                val groceryActionIntent = Intent(this, GroceryActivity::class.java)
                startActivity(groceryActionIntent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}