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

    private var currentPage = 0

    private val pageSize = 10

    private var isLoadingMore = false


    init {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelScope.launch {


            try {
                val products = repository.getProducts(limit = pageSize, skip = 0)
                currentPage = 1
                _state.value = ProductUiState.Success(products)

            } catch (e: Exception) {
                _state.value = ProductUiState.Error(
                    e.message ?: "Something went wrong"
                )
            }

        }


    }

    fun loadNextPage() {

        if (isLoadingMore) return

        isLoadingMore = true
        val currentState = _state.value as? ProductUiState.Success ?: return
        if (!currentState.hasMore) return

        _state.value = currentState.copy(isLoadingMore = true)

        viewModelScope.launch {

            try {

                val newProducts =
                    repository.getProducts(
                        limit = pageSize,
                        skip = currentPage * pageSize
                    )

                val hasMore = newProducts.size == pageSize
                _state.value = ProductUiState.Success(
                    products = currentState.products + newProducts,
                    isLoadingMore = false,
                    hasMore = hasMore
                )

                currentPage++

            } catch (e: Exception) {
                _state.value = currentState.copy(
                    isLoadingMore = false
                )
            } finally {

                isLoadingMore = false
            }
        }
    }

    fun refreshProducts() {

        if (isLoadingMore) return

        viewModelScope.launch {

            _state.value =
                ProductUiState.Success(
                    products = emptyList(),
                    isRefreshing = true
                )

            try {

                val products = repository.getProducts(
                    limit = pageSize,
                    skip = 0
                )

                currentPage = 1

                _state.value = ProductUiState.Success(
                    products = products,
                    isRefreshing = false,
                    hasMore = products.size == pageSize
                )

            } catch (e: Exception) {

                _state.value =
                    ProductUiState.Error(
                        e.message ?: "Refresh failed"
                    )
            }
        }
    }


}