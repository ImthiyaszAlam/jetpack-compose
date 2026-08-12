package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imthiyas.usermanagementapp.ui.components.AppTopBar
import com.imthiyas.usermanagementapp.ui.components.ProductCard
import com.imthiyas.usermanagementapp.ui.components.ProductDetailsContent
import com.imthiyas.usermanagementapp.ui.state.ProductUiState
import com.imthiyas.usermanagementapp.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: String?,
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = viewModel()
) {


    Scaffold(topBar = { AppTopBar("Details", onBackClick = onBackClick) }) { innerPadding ->


        LaunchedEffect(productId) {
            productId?.toIntOrNull()?.let {
                viewModel.loadProductDetails(it)
            }
        }

        val state by viewModel.state.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            when (state) {

                is ProductUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp).align(Alignment.Center))
                }

                is ProductUiState.Success -> {
                    val product =
                        (state as ProductUiState.Success).products.firstOrNull()

                    product?.let {
                        ProductDetailsContent(
                            it,
                        )
                    }
                }

                is ProductUiState.Error -> {
                    Text("Error: ${(state as ProductUiState.Error).message}")
                }
            }

        }
    }
}