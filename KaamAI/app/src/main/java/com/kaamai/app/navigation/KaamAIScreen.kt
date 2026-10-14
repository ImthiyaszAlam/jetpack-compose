package com.kaamai.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

enum class KaamAIScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    Home("home", "Home", Icons.Default.Home),
    Reports("reports", "Reports", Icons.Default.Description),
    Notifications("notifications", "Notifications", Icons.Default.Notifications);

    companion object {
        fun fromRoute(route: String?): KaamAIScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Reports.route -> Reports
                Notifications.route -> Notifications
                null -> Home
                else -> Home
            }
    }
}
