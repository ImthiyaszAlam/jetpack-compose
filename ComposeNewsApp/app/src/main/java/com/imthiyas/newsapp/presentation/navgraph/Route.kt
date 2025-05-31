package com.imthiyas.newsapp.presentation.navgraph

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "OnBoardingScreen")
    object HomeScreen : Route(route = "HomeScreen")
    object SearchScreen : Route(route = "SearchScreen")
    object BookmarkScreen : Route(route = "BookmarkScreen")
    object DetailScreen : Route(route = "DetailScreen")

    object AppStartNavigation : Route(route = "AppStartNavigation")
    object NewsNavigation : Route(route = "NewsNavigation")
    object NewsNavigatorScreen : Route(route = "NewsNavigatorScreen")
}