package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    private val _order = MutableLiveData(Order())
    val order: LiveData<Order> get() = _order

    fun updateMainMeal(mealName: String) {
        val currentOrder = _order.value ?: Order()
        currentOrder.mainMeal = mealName
        _order.value = currentOrder
    }

    fun updateSideDish(dishName: String, isChecked: Boolean) {
        val currentOrder = _order.value ?: Order()

        if (isChecked) {
            if (!currentOrder.sideDishes.contains(dishName)) {
                currentOrder.sideDishes.add(dishName)
            }
        } else {
            currentOrder.sideDishes.remove(dishName)
        }

        _order.value = currentOrder
    }

    fun updateDrink(drinkName: String) {
        val currentOrder = _order.value ?: Order()
        currentOrder.drink = drinkName
        _order.value = currentOrder
    }

    fun resetOrder() {
        _order.value = Order()
    }
}