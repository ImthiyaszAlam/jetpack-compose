package com.imthiyas.tweetsapp.repository

import android.util.Log
import com.imthiyas.tweetsapp.models.Tweet
import com.imthiyas.tweetsapp.network.TweetsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsApi: TweetsApi) {
    val TAG = "Response"


    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>>
        get() = _tweets


    suspend fun getCategoriesRepo() {
        val response = tweetsApi.getCategories()
        Log.d(TAG, "$response")
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }


    suspend fun getTweetsRepo(category: String) {
        val response = tweetsApi.getTweets(category)
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }
    }
}