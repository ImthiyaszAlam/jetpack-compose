package com.imthiyas.cmpdemo.domain.usecase

import com.imthiyas.cmpdemo.domain.model.Category
import com.imthiyas.cmpdemo.domain.repository.FoodRepository

class GetCategoriesUseCase(private val repository: FoodRepository) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}
