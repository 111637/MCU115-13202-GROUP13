package com.example.myapplication

data class Order(
    var mainMeal: String? = null,
    val sideDishes: MutableList<String> = mutableListOf(),
    var drink: String? = null
) {
    fun checkIsValid(): Boolean {
        val hasMain = !mainMeal.isNullOrEmpty()
        val hasSide = sideDishes.isNotEmpty()
        val hasDrink = !drink.isNullOrEmpty()

        return hasMain && hasSide && hasDrink
    }
}