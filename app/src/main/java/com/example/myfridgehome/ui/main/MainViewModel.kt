package com.example.myfridgehome.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfridgehome.dto.AddFoodEvent
import com.example.myfridgehome.dto.Food
import com.example.myfridgehome.dto.Recipes
import com.example.myfridgehome.service.FoodService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel : ViewModel() {
    var recipes: MutableLiveData<ArrayList<Recipes>> = MutableLiveData<ArrayList<Recipes>>()
    private var _foodService: FoodService = FoodService()
    private lateinit var firestore: FirebaseFirestore
    private var _food = Food()
    private var _events = MutableLiveData<List<AddFoodEvent>>()
    private var _foodItems : MutableLiveData<ArrayList<Food>> = MutableLiveData<ArrayList<Food>>()

    init{
        fetchFoodItems()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToFoodItems()
    }

    private fun listenToFoodItems() { //listens for firebase updates
        firestore.collection("foodItems").addSnapshotListener {
            snapshot, e ->
            //skip on exception
            if(e != null){
                Log.w(TAG, "Listen failed", e)
                return@addSnapshotListener
            }
            if (snapshot != null){//snapshot has been populated
                val allFoodItems = ArrayList<Food>()
                val documents = snapshot.documents
                documents.forEach{
                    val foodObject = it.toObject(Food::class.java)
                    if(foodObject !=null){
                        allFoodItems.add(foodObject!!)
                    }
                }
                _foodItems.value = allFoodItems
            }
        }
    }

    fun fetchFoodItems() {
        recipes = _foodService.fetchFoods()
    }

    internal fun save(event: AddFoodEvent){
        val collection = firestore.collection("foodItems").document(food.foodID).collection("events")
        val task = collection.add(event)
        task.addOnSuccessListener {
            event.foodID = it.id
        }
        task.addOnFailureListener {
            var message = it.message
        }
    }
    internal fun fetchEvents(){
        val eventsCollection = firestore.collection("foodItems")
            .document(food.foodID)
            .collection("events")
        eventsCollection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            var innerEvents = querySnapshot?.toObjects(AddFoodEvent::class.java)
            _events.postValue(innerEvents)
        }
    }
    fun saveFoodItem(food: AddFoodEvent) {
        val document=
            if (!food.foodID.isEmpty()) {
                firestore.collection("foodItems").document(food.foodID) //update an existing entry
            }else{
                firestore.collection("foodItems").document() //create a new entry if the id does not exist already
            }
        food.foodID = document.id
        val set =document.set(food)
            set.addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "document failed")
            }
    }
    internal var food: Food
        get() {return _food}
        set(value) {_food = value}

    internal var events: MutableLiveData<List<AddFoodEvent>>
        get() {return _events}
        set(value) {_events = value}
    internal var foodItems : MutableLiveData<ArrayList<Food>>
        get() {return _foodItems}
        set(value) {_foodItems = value}
}
