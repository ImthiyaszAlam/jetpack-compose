package com.imthiyas.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

}