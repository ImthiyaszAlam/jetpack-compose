package com.imthiyas.usermanagementapp.ui.state

import com.imthiyas.usermanagementapp.model.Product

sealed class ProductUiState {

    data object Loading : ProductUiState()

    data class Success(val products: List<Product>) : ProductUiState()

    data class Error(val message: String) : ProductUiState()

}