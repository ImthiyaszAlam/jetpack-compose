package com.imthiyas.quoteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imthiyas.quoteapp.models.Quote

@Composable
fun MainScreen(data: Array<Quote>, onClick: () -> Unit) {
    Column {
        Text(
            text = "Quote-App", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp, bottom = 25.dp)
        )
        QuoteList(data = data) {
            onClick()
        }
    }
}