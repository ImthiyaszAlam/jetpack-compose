package com.imthiyas.feature_restaurantlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RestaurantListScreen(){

    Column(modifier = Modifier.padding(11dp)) {
        Text("Name")
    }

    Row {
        Text("Enter Name")
        Column(modifier = Modifier.padding(11dp)) {
            Text("Name")
        }
    }
    
}
