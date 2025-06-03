package com.imthiyas.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import com.imthiyas.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    var splashCondition: State<Boolean> = _splashCondition

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
                if (shouldStartFromHomeScreen) {
                    startDestination = Route.NewsNavigation.route
                } else {
                    startDestination = Route.AppStartNavigation.route
                }
                delay(300)
                _splashCondition.value = false
            }
        }

    }

}