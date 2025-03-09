package com.imthiyas.tweetsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imthiyas.tweetsapp.network.TweetsApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val TAG = "Response"

    @Inject
    private lateinit var tweetsApi: TweetsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val response = tweetsApi.getCategories()
            Log.d(TAG, response.body().toString())
            val result = response.body()
            result?.distinct()

            Log.d(TAG, result.toString())
        }

    }
}