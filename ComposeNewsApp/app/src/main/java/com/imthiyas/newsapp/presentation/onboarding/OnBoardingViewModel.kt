package com.imthiyas.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class OnBoardingViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {


    fun onEvent(event: OnBoardingEvent) {

        when (event) {
             OnBoardingEvent.saveAppEntry -> {
                saveAppEntry()
            }
        }
    }


    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}