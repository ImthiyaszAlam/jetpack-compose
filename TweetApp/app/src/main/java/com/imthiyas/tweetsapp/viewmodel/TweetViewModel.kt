package com.imthiyas.tweetsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imthiyas.tweetsapp.models.Tweet
import com.imthiyas.tweetsapp.repository.TweetRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TweetViewModel @Inject constructor( val repository: TweetRepository) : ViewModel() {
    val tweet: StateFlow<List<Tweet>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            repository.getTweetsRepo("motivation")
        }
    }
}