package com.imthiyas.quoteapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imthiyas.quoteapp.models.Quote

@Composable
fun MainScreen(data: Array<Quote>, onClick: () -> Unit) {
    Column {
        Text(
            text = "Quote-App", color = Color.White, style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp, bottom = 25.dp)
                .border(1.dp, Color.Gray)
        )
        QuoteList(data = data) {
            onClick()
        }
    }
}

enum class Pages {
    LISTINGS,
    DETAILS
}