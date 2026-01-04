package com.imthiyas.composelearning.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.imthiyas.composelearning.ui.screen.home.HomeAction
import io.reactivex.functions.Action

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeAction)-> Unit
) {
    Text(text = "Home")
}