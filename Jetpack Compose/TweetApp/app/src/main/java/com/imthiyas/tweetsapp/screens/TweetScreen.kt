package com.imthiyas.tweetsapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imthiyas.tweetsapp.viewmodel.TweetViewModel


@Composable
fun TweetDetails() {
    val tweetViewModel: TweetViewModel = viewModel()
    val tweets = tweetViewModel.tweet.collectAsState()

    LazyColumn(content = {
        items(tweets.value) {
            Tweet(tweet = it.text)
        }
    })
}

@Composable
fun Tweet(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(44.dp),
        border = BorderStroke(1.dp, Color.Gray),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}