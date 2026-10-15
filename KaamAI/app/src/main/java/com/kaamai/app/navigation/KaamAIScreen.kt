package com.kaamai.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.ui.graphics.vector.ImageVector

enum class KaamAIScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    Home("home", "Home", Icons.Default.Home),
    Reports("reports", "Reports", Icons.Default.Description),
    Notifications("notifications", "Notifications", Icons.Default.Notifications),
    NewVisit("new_visit", "New Visit", Icons.Default.Add),

    CustomerReportDetails("customer_report_details", "Report Details", Icons.Default.Add),
    ReviewConfirm("review", "Review & Confirm", Icons.Default.Reviews);

    companion object {
        fun fromRoute(route: String?): KaamAIScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Reports.route -> Reports
                Notifications.route -> Notifications
                NewVisit.route -> NewVisit
                ReviewConfirm.route -> KaamAIScreen.ReviewConfirm
                null -> Home
                else -> Home
            }
    }
}
