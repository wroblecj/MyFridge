package com.example.myfridgehome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.*
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.example.myfridgehome.ui.main.*
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var detector: GestureDetectorCompat
    private lateinit var newFragment: AddFoodItemEventFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment = MainFragment.newInstance()
        newFragment = AddFoodItemEventFragment.newInstance()

        detector = GestureDetectorCompat(this, MyFridgeGestureListener())




//        val languages = resources.getStringArray(R.array.MenuContents)

        BtnRecipes.setOnClickListener{
            val recipeIntent = Intent(this, RecipeActivity::class.java)
            startActivity(recipeIntent)
        }

        BtnFvtRecipes.setOnClickListener{
            val favoriteIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(favoriteIntent)

        }

        BtnMyFridge.setOnClickListener{
            val fridgeIntent = Intent(this, MyFridgeActivity::class.java)
            startActivity(fridgeIntent)
        }

        BtnGrocery.setOnClickListener{
            val groceryIntent = Intent(this, GroceryActivity::class.java)
            startActivity(groceryIntent)

        }

}
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    inner class MyFridgeGestureListener : GestureDetector.SimpleOnGestureListener() {

        private val  SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if (Math.abs(diffX) > Math.abs(diffY)) {
                // this is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 ) {
                        // right swipe
                        this@MainActivity.onSwipeRight()
                    } else {
                        // left swipe.
                        this@MainActivity.onLeftSwipe()
                    }
                    true
                } else  {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            } else {
                // this is either a bottom or top swipe.
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        this@MainActivity.onSwipeTop()
                    } else {
                        this@MainActivity.onSwipeBottom()
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }


        }
    }

    private fun onSwipeBottom() {
        Toast.makeText(this, "Swiped Bottom", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeTop() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, newFragment)
            .commitNow()
        activeFragment = newFragment
    }

    private fun onLeftSwipe() {
        Toast.makeText(this, "Swiped Left", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeRight() {
        Toast.makeText(this, "Swiped Right", Toast.LENGTH_LONG).show()
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
