package com.imthiyas.cmpdemo.domain.usecase

import com.imthiyas.cmpdemo.domain.model.FoodItem
import com.imthiyas.cmpdemo.domain.repository.FoodRepository

class GetFoodItemsUseCase(private val repository: FoodRepository) {
    suspend operator fun invoke(): List<FoodItem> {
        return repository.getFoodItems()
    }
}
