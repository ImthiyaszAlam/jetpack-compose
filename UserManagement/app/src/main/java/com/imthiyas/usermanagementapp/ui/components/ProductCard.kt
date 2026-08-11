package com.imthiyas.usermanagementapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.model.Product

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {


    var isFavourite by remember {
        mutableStateOf(product.isFavourite)
    }

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(top = 16.dp)
            .clickable{onClick()},
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