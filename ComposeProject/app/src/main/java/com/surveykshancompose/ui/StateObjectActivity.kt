package com.surveykshancompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class StateObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NotificationScreen() }
    }
}

@Preview
@Composable
fun NotificationScreen() {

    val count: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()  // ✅ Fixed (removed parameter)
    ) {

        NotificationCounter(count.value, { count.value++ })
        MessageBar()
    }
}

@Composable
fun NotificationCounter(count: Int, Increment: () -> Unit) {
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = "You have sent ${count} notifications", color = Color.White)
        Button(onClick = {
            Increment()
        }) {
            Text(text = "Send Notification")
        }
    }
}

@Composable
fun MessageBar() {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Favorite,  // ✅ Fixed syntax
                contentDescription = "Favorite",
                modifier = Modifier.padding(4.dp)
            )

            Text(text = "Messages sent so far: 10")
        }
    }
}
