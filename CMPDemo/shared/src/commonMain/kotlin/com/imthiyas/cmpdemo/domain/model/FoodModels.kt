package com.imthiyas.cmpdemo.domain.model

data class FoodItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val rating: Double,
    val discountText: String? = null,
    val deliveryTime: String = "25-30 mins",
    val distance: String = "2.1 km",
    val cuisines: String = "Fast Food, Beverages",
    val isBestseller: Boolean = false,
    val emoji: String = "🍔"
)

data class CartItem(
    val foodItem: FoodItem,
    var quantity: Int
)

enum class BottomNavTab(val title: String) {
    ORDER("Order"),
    DINING("Dining"),
    OFFERS("Offers"),
    PROFILE("Profile")
}

data class Category(
    val id: String,
    val name: String,
    val emoji: String
)
