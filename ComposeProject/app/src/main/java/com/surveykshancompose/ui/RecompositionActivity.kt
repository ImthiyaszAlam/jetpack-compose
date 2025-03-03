package com.surveykshancompose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.surveykshancompose.R

class RecompositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Recomposable() }
    }
}


@Preview
@Composable
fun Recomposable() {
    val state = remember { mutableStateOf(0.0) }
    Log.d("DEBUG", "value initial: $state ")

    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d("DEBUG", "before and after recomp ")
        Text(text = state.value.toString())
        Log.d("DEBUG", "${state.value}")
    }
}