package com.imthiyas.usermanagementapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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