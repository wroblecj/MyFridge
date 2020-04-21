package com.example.myfridgehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.example.myfridgehome.ui.main.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {
    private lateinit var detector: GestureDetectorCompat

    private lateinit var mainFragment: MainFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        detector = GestureDetectorCompat(this, FridgeGestureListener())
    }
    inner class FridgeGestureListener : GestureDetector.SimpleOnGestureListener(){

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
        Toast.makeText(this, "Bottom Swipe", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeTop() {
        Toast.makeText(this, "Top Swipe", Toast.LENGTH_LONG).show()
    }

    internal fun onLeftSwipe() {
        Toast.makeText(this, "Left Swipe", Toast.LENGTH_LONG).show()
    }
    internal fun onSwipeRight() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddFoodItemEventFragment.newInstance())
            .commitNow()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.my_fridge_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ActionBtnHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
                return true
            }
            R.id.ActionBtnRecipes -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RecipesFragment.newInstance())
                    .commitNow()
                return true
            }
            R.id.ActionBtnFavorites -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FavoritesFragment.newInstance())
                    .commitNow()
                return true
            }
            R.id.ActionBtnMyFridge -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MyFridgeFragment.newInstance())
                    .commitNow()
                return true
            }
            R.id.ActionBtnGrocery -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GroceryListFragment.newInstance())
                    .commitNow()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
