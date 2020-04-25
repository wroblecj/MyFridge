package com.example.myfridgehome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfridgehome.dto.Food
import com.example.myfridgehome.ui.main.MainViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith


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
    fun confirmStoredFoods_OutputsChicken(){
        var chicken: Food = Food("chicken", "meat", "1", "whole")
        assertEquals("chicken", chicken.toString())
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
        mvm.fetchFoodItems()
    }
    private fun thenResultContainsChicken() {
        var chickenFound = false

        mvm.recipes.observeForever{
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach{
                if(it.type.contains("chicken")){
                    chickenFound = true
                }
            }
            assertTrue(chickenFound)
        }
    }
}
