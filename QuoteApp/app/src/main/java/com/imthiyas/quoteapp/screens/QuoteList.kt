package com.imthiyas.quoteapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.imthiyas.quoteapp.models.Quote


@Composable
fun QuoteList(data: Array<Quote>, onClick: () -> Unit) {
    LazyColumn(content = {
        items(data) {
            QuoteItem(quote = it) { onClick() }
        }


    })
}