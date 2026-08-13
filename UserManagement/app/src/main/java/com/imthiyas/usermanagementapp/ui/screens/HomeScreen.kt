package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.ui.components.AppTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProductsClick: () -> Unit,
    onFormsClick: () -> Unit,
    onPlayClick: () -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(topBar = { AppTopBar("Home", onBackClick = null) }) { innerPadding ->


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
        ) {
            Text(text = "Welcome", modifier = Modifier.fillMaxWidth())
            Text(text = "Learn Jetpack Compose", modifier = Modifier.fillMaxWidth())


            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(onClick = { onProductsClick() }, modifier = Modifier.weight(1f)) {
                    Text("Products")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { onFormsClick() },
                    modifier = Modifier.weight(1f)
                ) { Text("Forms") }


            }

            Button(
                onClick = { onPlayClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Play Ground")
            }


        }
    }
}
