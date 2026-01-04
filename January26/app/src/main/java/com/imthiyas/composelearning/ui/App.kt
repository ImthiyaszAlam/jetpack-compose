package com.imthiyas.composelearning.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imthiyas.composelearning.ui.navigation.AppNavGraph

@Composable
fun App() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavGraph()
    }
}