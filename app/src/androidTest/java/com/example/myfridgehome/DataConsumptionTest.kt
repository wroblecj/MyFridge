package com.example.myfridgehome

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfridgehome.dto.Recipe
import com.example.myfridgehome.service.RecipeService
import com.example.myfridgehome.ui.main.MainViewModel
import io.mockk.mockk

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DataConsumptionTest {
    @get:Rule
    lateinit var mvm:MainViewModel

    var recipeService = mockk<RecipeService>()

    @Test
    fun confirmSearchChicken_outputsChickenRecipe () {
        var recipe: Recipe = Recipe("Cercis", "chicken")
        assertEquals("chicken", recipe.toString());
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
