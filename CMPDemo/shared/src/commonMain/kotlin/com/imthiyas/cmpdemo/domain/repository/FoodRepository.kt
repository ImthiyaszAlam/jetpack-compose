package com.imthiyas.cmpdemo.domain.repository

import com.imthiyas.cmpdemo.domain.model.Category
import com.imthiyas.cmpdemo.domain.model.FoodItem

interface FoodRepository {
    suspend fun getFoodItems(): List<FoodItem>
    suspend fun getCategories(): List<Category>
}
