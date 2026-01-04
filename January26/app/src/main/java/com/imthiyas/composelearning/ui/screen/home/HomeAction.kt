package com.imthiyas.composelearning.ui.screen.home

sealed interface HomeAction {
    data object OnRetryClick : HomeAction
    data object OnRefresh : HomeAction
}