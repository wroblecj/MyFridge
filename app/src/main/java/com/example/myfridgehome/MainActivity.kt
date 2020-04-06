package com.example.myfridgehome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*


open class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val languages = resources.getStringArray(R.array.MenuContents)

        val recipeButton = findViewById<Button>(R.id.BtnRecipes)
        recipeButton.setOnClickListener{
            val recipeIntent = Intent(this, RecipeActivity::class.java)
            startActivity(recipeIntent)
        }

        val fvtButton = findViewById<Button>(R.id.BtnFvtRecipes)
        fvtButton.setOnClickListener{
            val favoriteIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(favoriteIntent)
        }

        val fridgeButton = findViewById<Button>(R.id.BtnMyFridge)
        fridgeButton.setOnClickListener{
            val fridgeIntent = Intent(this, MyFridgeActivity::class.java)
            startActivity(fridgeIntent)
        }

        val groceryButton = findViewById<Button>(R.id.BtnGrocery)
        groceryButton.setOnClickListener{
            val groceryIntent = Intent(this, GroceryActivity::class.java)
            startActivity(groceryIntent)
        }

//        val recipeButtonAction = findViewById<Button>(R.id.ActionBtnRecipes)
//        recipeButtonAction.setOnClickListener{
//            val recipeActionIntent = Intent(this, RecipeActivity::class.java)
//            startActivity(recipeActionIntent)
//        }
//
//        val fvtButtonAction = findViewById<Button>(R.id.ActionBtnFavorites)
//        fvtButtonAction.setOnClickListener{
//            val favoriteActionIntent = Intent(this, FavoriteActivity::class.java)
//            startActivity(favoriteActionIntent)
//
//        }
//
//        val fridgeButtonAction = findViewById<Button>(R.id.ActionBtnMyFridge)
//        fridgeButtonAction.setOnClickListener{
//            val fridgeActionIntent = Intent(this, MyFridgeActivity::class.java)
//            startActivity(fridgeActionIntent)
//        }
//
//        val groceryButtonAction = findViewById<Button>(R.id.ActionBtnGrocery)
//        groceryButtonAction.setOnClickListener{
//            val groceryActionIntent = Intent(this, GroceryActivity::class.java)
//            startActivity(groceryActionIntent)
//
//        }

//                val spinner = findViewById<Spinner>(R.id.spinner)
//                if (spinner != null) {
//                    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
//                    spinner.adapter = adapter
//
//                    spinner.onItemSelectedListener = object :
//
//                        AdapterView.OnItemSelectedListener {
//                        override fun onNothingSelected(parent: AdapterView<*>?) {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }
//
//                        override fun onItemSelected(
//                            parent: AdapterView<*>?,
//                            view: View?,
//                            position: Int,
//                            id: Long
//                        ) {
//                            Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " +
//                                    "" + languages[position], Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }


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
