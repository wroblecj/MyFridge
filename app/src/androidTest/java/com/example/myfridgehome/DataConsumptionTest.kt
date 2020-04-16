package com.example.myfridgehome

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfridgehome.ui.main.MainViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.util.logging.Handler

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DataConsumptionTest {
    @get:Rule
    var rule: TestRule =  InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun dataFeedTest() {
        givenAFeedOfFoodItemsAreAvailable()
        whenSearchForChicken()
        thenResultContainsChicken()
    }

    private fun givenAFeedOfFoodItemsAreAvailable() {
        mvm = MainViewModel()
    }
    private fun whenSearchForChicken() {
        mvm.fetchRecipes("chicken")
    }
    private fun thenResultContainsChicken() {
        var chickenFound = false

        mvm.recipes.observeForever{
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach{
                if(it.mainIngredient.contains("chicken")){
                    chickenFound = true
                }
            }
            assertTrue(chickenFound)
        }
    }
}
