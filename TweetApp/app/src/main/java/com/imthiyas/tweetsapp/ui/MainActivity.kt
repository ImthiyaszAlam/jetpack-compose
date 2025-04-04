package com.imthiyas.tweetsapp.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imthiyas.tweetsapp.network.TweetsApi
import com.imthiyas.tweetsapp.screens.CategoryScreen
import com.imthiyas.tweetsapp.screens.TweetDetails
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


@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen()
        }

        composable(route = "details/{category}",
            arguments = listOf(
                navArgument("category")
            ) {
                type = NavType.StringType
            }
        ) {
            TweetDetails()
        }
    }
}


@Composable
fun App1() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "registration"
    ) {
        composable(route = "registration") {
            RegistrationScreen {
                navController.navigate("main/${it}")
            }
        }

        composable(route = "login") {
            LoginScreen()
        }
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
            val email = it.arguments?.getString("email")
            CategoryScreen(email)
        }
    }
}

@Composable
fun RegistrationScreen(onClick: (email: String) -> Unit) {
    Text(
        text = "Registration",
        modifier = Modifier.clickable {
            onClick("alam@gmail.com")
        },
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