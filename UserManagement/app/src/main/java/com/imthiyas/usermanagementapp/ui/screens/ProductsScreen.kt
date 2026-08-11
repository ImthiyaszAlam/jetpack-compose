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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.ui.components.AppTopBar
import com.imthiyas.usermanagementapp.ui.components.ProductCard

@Composable
fun ProductsScreen(onProductClick: (Int) -> Unit, onBackClick: () -> Unit) {

    Scaffold(topBar = { AppTopBar("Products", onBackClick = onBackClick) }) { innerPadding ->


        val products = listOf(
            Product(1, "iPhone", "₹99,999", true),
            Product(2, "Samsung", "₹87,876", false),
            Product(3, "Pixel", "₹69,999", true),
            Product(4, "OnePlus", "₹49,999", false),
            Product(5, "Nothing Phone", "₹39,999", true),
            Product(6, "Xiaomi", "₹29,999", false)
        )


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

}