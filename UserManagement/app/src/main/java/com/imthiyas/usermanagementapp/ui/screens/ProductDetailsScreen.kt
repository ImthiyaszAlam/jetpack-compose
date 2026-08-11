package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.ui.components.AppTopBar

@Composable
fun ProductDetailsScreen(productId: String?, onBackClick: () -> Unit) {

    Scaffold(topBar = { AppTopBar("Details", onBackClick = onBackClick) }) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
        ) {

            Text(
                text = "Product Details"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Product ID: $productId"
            )
        }

    }
}