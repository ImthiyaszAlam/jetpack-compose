package com.imthiyas.cmpdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    var count by remember {
        mutableStateOf(1)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { count-- }) {
                Text("Decrease")
            }


            Button(onClick = { count++ }) {
                Text("Increase")
            }

        }


        OutlinedTextField(
            value = count.toString(),
            onValueChange = { newValue ->
                newValue.toIntOrNull()?.let { count = it }
            },
            label = { Text("Count") }
        )

    }


}