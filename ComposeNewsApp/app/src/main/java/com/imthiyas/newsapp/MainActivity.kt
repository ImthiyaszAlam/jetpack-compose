package com.imthiyas.newsapp

import android.R
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import com.imthiyas.newsapp.presentation.navgraph.NavGraph
import com.imthiyas.newsapp.presentation.onboarding.OnBoardingEvent
import com.imthiyas.newsapp.presentation.onboarding.OnBoardingViewModel
import com.imthiyas.newsapp.presentation.onboarding.OnboardingScreen
import com.imthiyas.newsapp.presentation.onboarding.components.OnboardingPage
import com.imthiyas.newsapp.presentation.onboarding.pages
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition.value
            }

            val value = viewModel.splashCondition.value
            Log.d("setKeepOnScreenCondition", "$value")
        }


        setContent {
            val isSystemInDarkMode = isSystemInDarkTheme()
            val systemController = rememberSystemUiController()

            SideEffect {
                systemController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isSystemInDarkMode
                )
            }

            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
            {
                val startDestination = viewModel.startDestination
                NavGraph(startDestination = startDestination)
                Log.d("startDestination", startDestination)
            }
        }
    }
}




