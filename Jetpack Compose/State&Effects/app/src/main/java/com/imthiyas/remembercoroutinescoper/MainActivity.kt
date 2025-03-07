package com.imthiyas.remembercoroutinescoper

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // LaunchEffectComposable()
            App()
        }
    }
}


@Composable
fun App() {
    val counter = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        counter.value = 10
    }
    Counter(counter.value)

}


@Composable
fun Counter(value: Int) {
    val state = rememberUpdatedState(newValue = value)
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        Log.d("Alam", state.value.toString())
    }
    Text(text = value.toString())
}

@Composable
fun LaunchEffectComposable() {
    val counter = remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = Unit) {

    }

    var text = "Counter is running ${counter.intValue}"
    if (counter.intValue == 10) {
        text = "Counter Stopped"
    }

    Column {
        Text(text = text)
        Button(onClick = {
            scope.launch {
                Log.d("LaunchedEffect", "LaunchedEffect Started")
                try {
                    for (i in 1..10) {
                        counter.intValue++
                        delay(1000)
                    }
                } catch (e: Exception) {
                    val message = e.message ?: "Unknown Error"
                    Log.d("LaunchedEffect", "Exception: ${message.toString()}")

                }
            }
        }) {
            Text(text = "Start")
        }
    }

    Text(
        text = text,
        color = Color.White,
        modifier = Modifier.padding(start = 20.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive
    )
}