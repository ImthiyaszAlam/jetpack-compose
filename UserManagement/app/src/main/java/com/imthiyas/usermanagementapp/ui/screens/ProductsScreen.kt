package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.ui.components.AppTopBar
import com.imthiyas.usermanagementapp.ui.components.ProductCard
import com.imthiyas.usermanagementapp.ui.state.ProductUiState
import com.imthiyas.usermanagementapp.viewmodel.ProductViewModel

@Composable
fun ProductsScreen(
    onProductClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: ProductViewModel= viewModel()
) {

    Scaffold(topBar = { AppTopBar("Products", onBackClick = onBackClick) }) { innerPadding ->


        val state by viewModel.state.collectAsState()
        when (state) {

            is ProductUiState.Loading -> {
                Text(text = "Loading")
            }

            is ProductUiState.Success -> {
             val products = (state as ProductUiState.Success).products


                var searchText by remember {
                    mutableStateOf("")
                }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)
                        .padding(innerPadding)
                ) {


                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        label = { Text("Search Products") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )


                    val filterProducts = products.filter {
                        it.name.contains(
                            searchText, ignoreCase = true
                        ) ||

                                it.price.contains(searchText, ignoreCase = true)
                    }

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {

                        items(filterProducts) { product ->
                            ProductCard(
                                product = product,
                                onClick = {
                                    onProductClick(product.id)
                                }
                            )
                        }

                    }

                }



            }

            is ProductUiState.Error -> {
                Text("Error: ${(state as ProductUiState.Error).message}")
            }

        }





    }

}