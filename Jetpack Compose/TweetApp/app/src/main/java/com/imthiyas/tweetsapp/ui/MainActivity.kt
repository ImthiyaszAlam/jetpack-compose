package com.imthiyas.tweetsapp.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            App()
        }

    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "registration"
    ) {
        composable(route = "registration") {
            RegistrationScreen()
        }

        composable(route = "login") {
            LoginScreen()
        }
        composable(route = "main") {
            CategoryScreen()
        }
    }
}

@Composable
fun RegistrationScreen() {
    Text(
        text = "Registration",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun LoginScreen() {
    Text(
        text = "Login",
        style = MaterialTheme.typography.bodyLarge
    )
}