package com.imthiyas.composefeatures

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Counter()
        }
    }
}


@Composable
fun Counter() {
    var count = remember { mutableStateOf(0) }

    val key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d("COUNTONE", "${count.value}")
    }


    Button(onClick = { count.value++ }) {
        Text(text = "Increment Count")
    }
}

@Composable
fun ListComposable() {
    val categoryState = remember { mutableStateOf(emptyList<String>()) }
    categoryState.value = fetchCategories()

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 50.dp, top = 10.dp)
    ) {
        items(categoryState.value) { item ->
            Text(
                text = item,
                color = Color.White
            )
        }
    }
}

fun fetchCategories(): List<String> {
    return listOf("A", "B", "C")
}
