package com.imthiyas.usermanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imthiyas.usermanagementapp.repository.ProductRepository
import com.imthiyas.usermanagementapp.ui.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {

    private val repository = ProductRepository()
    private val _state = MutableStateFlow<ProductUiState>(ProductUiState.Loading)

    val state: StateFlow<ProductUiState> = _state


    fun loadProductDetails(id: Int) {
        viewModelScope.launch {
            try {
                val productDetails = repository.getProductDetails(id)
                _state.value = ProductUiState.Success(listOf(productDetails))


            } catch (e: Exception) {
                _state.value =
                    ProductUiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }


    }

}