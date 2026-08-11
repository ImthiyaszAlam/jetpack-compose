package com.imthiyas.usermanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.repository.ProductRepository
import com.imthiyas.usermanagementapp.ui.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _state = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val state: StateFlow<ProductUiState> = _state

    init {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelScope.launch {


            try {
                val products = repository.getProducts()
                _state.value = ProductUiState.Success(products)

            } catch (e: Exception) {
                _state.value = ProductUiState.Error(
                    e.message ?: "Something went wrong"
                )
            }

        }


    }

}