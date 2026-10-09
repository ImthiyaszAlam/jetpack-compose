package com.imthiyas.cmpdemo.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imthiyas.cmpdemo.data.repository.FoodRepositoryImpl
import com.imthiyas.cmpdemo.domain.model.BottomNavTab
import com.imthiyas.cmpdemo.domain.model.CartItem
import com.imthiyas.cmpdemo.domain.model.Category
import com.imthiyas.cmpdemo.domain.model.FoodItem
import com.imthiyas.cmpdemo.domain.usecase.GetCategoriesUseCase
import com.imthiyas.cmpdemo.domain.usecase.GetFoodItemsUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = FoodRepositoryImpl()
    private val getFoodItemsUseCase = GetFoodItemsUseCase(repository)
    private val getCategoriesUseCase = GetCategoriesUseCase(repository)

    var selectedTab by mutableStateOf(BottomNavTab.ORDER)
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    var selectedFilter by mutableStateOf("All")
        private set

    var searchQuery by mutableStateOf("")
        private set

    var foodItems = mutableStateListOf<FoodItem>()
        private set

    var categories = mutableStateListOf<Category>()
        private set

    val cartItems = mutableStateListOf<CartItem>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            foodItems.clear()
            foodItems.addAll(getFoodItemsUseCase())

            categories.clear()
            categories.addAll(getCategoriesUseCase())
        }
    }

    fun selectTab(tab: BottomNavTab) {
        selectedTab = tab
    }

    fun selectCategory(category: String) {
        selectedCategory = category
    }

    fun selectFilter(filter: String) {
        selectedFilter = filter
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun addToCart(item: FoodItem) {
        val existing = cartItems.find { it.foodItem.id == item.id }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(CartItem(foodItem = item, quantity = 1))
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        if (cartItem.quantity > 1) {
            cartItem.quantity--
        } else {
            cartItems.remove(cartItem)
        }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
