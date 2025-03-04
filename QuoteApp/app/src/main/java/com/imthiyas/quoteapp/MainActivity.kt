package com.imthiyas.quoteapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.imthiyas.quoteapp.screens.MainScreen
import com.imthiyas.quoteapp.screens.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssetsFromFile(applicationContext)
        }
        setContent { App() }
    }
}

@Composable
fun App() {
    if (DataManager.isDataLoaded.value) {

        if (DataManager.currentPage.value == Pages.LISTINGS){
            MainScreen(data = DataManager.data) { }
        }else{
         //   QuoteDetails(quote=)
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Text(text = "Loading Data....", style = MaterialTheme.typography.labelSmall)

        }
    }
}