package com.imthiyas.tweetsapp.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.imthiyas.tweetsapp.network.TweetsApi
import com.imthiyas.tweetsapp.screens.CategoryScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val TAG = "Response"

    @Inject
    lateinit var tweetsApi: TweetsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategoryScreen()
        }

    }
}