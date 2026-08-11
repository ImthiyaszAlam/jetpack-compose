package com.imthiyas.usermanagementapp

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.model.Product
import com.imthiyas.usermanagementapp.ui.theme.UserManagementAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserManagementAppTheme() {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Compose Master")
                            }
                        )
                    }
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Welcome", modifier = Modifier.fillMaxWidth())
        Text(text = "Learn Jetpack Compose", modifier = Modifier.fillMaxWidth())


        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(onClick = {}, modifier = Modifier.weight(1f)) {
                Text("Users")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Camera") }


        }

        val products = listOf(
            Product("iPhone", "₹99,999", true),
            Product("Samsung", "₹87,876", false),
            Product("Pixel", "₹69,999", true),
            Product("OnePlus", "₹49,999", false),
            Product("Nothing Phone", "₹39,999", true),
            Product("Xiaomi", "₹29,999", false)
        )


        var searchText by remember {
            mutableStateOf("")
        }

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
                ProductCard(product)
            }

        }

    }
}

@Composable
fun ProductBox() {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke
            (1.dp, Color.Red),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Text(text = "Product", modifier = Modifier.align(Alignment.Center))

            Text(text = "❤\uFE0F", modifier = Modifier.align(Alignment.TopEnd))

            Text(text = "₹999", modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}

@Composable
fun ProductCard(product: Product) {


    var isFavourite by remember {
        mutableStateOf(product.isFavourite)
    }

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(text = product.name, modifier = Modifier.align(Alignment.TopCenter))

            if (product.isFavourite) {
                Text(
                    text = if (isFavourite) "❤️" else "♡",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            isFavourite = !isFavourite
                        }
                )
            }

            Text(text = product.price, modifier = Modifier.align(Alignment.BottomStart))

        }
    }

}



