package com.imthiyas.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import com.imthiyas.newsapp.presentation.onboarding.OnboardingScreen
import com.imthiyas.newsapp.presentation.onboarding.components.OnboardingPage
import com.imthiyas.newsapp.presentation.onboarding.pages
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("Test", it.toString())
            }
        }
        setContent {
            OnboardingScreen()
        }
    }
}


