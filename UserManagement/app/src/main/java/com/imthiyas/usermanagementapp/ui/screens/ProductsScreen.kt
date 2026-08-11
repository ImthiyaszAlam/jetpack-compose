package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

        val listState = rememberLazyListState()

        LaunchedEffect(listState) {

            snapshotFlow {
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            }.collect { lastVisibleItem ->

                val totalItems =
                    listState.layoutInfo.totalItemsCount

                if (
                    lastVisibleItem != null &&
                    lastVisibleItem >= totalItems - 2
                ) {
                    viewModel.loadNextPage()
                }
            }
        }



        val state by viewModel.state.collectAsStateWithLifecycle()
        when (state) {

            is ProductUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
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

                    LazyColumn( state = listState,modifier = Modifier.fillMaxWidth()) {

                        items((state as ProductUiState.Success).products) { product ->
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (state as ProductUiState.Error).message
                    )
                }
            }

        }





    }

}