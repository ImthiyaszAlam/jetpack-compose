package com.kaamai.app.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kaamai.app.ui.screens.HomeScreen
import com.kaamai.app.ui.screens.NewVisitScreen
import com.kaamai.app.ui.screens.ReportsScreen
import com.kaamai.app.viewmodel.KaamAIViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KaamAINavGraph(viewModel: KaamAIViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val screens = listOf(KaamAIScreen.Home, KaamAIScreen.Reports)
    val showTopBar = true
    val showBottomBar = currentRoute == null || currentRoute == KaamAIScreen.Home.route || currentRoute == KaamAIScreen.Reports.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "KaamAI",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(24.dp)
                )

                HorizontalDivider()

                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopAppBar(
                        title = {
                            when (currentRoute) {
                                KaamAIScreen.Reports.route -> {
                                    Text(
                                        text = "Reports",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                KaamAIScreen.NewVisit.route -> {
                                    Text(
                                        text = "New Visit Report",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                else -> {
                                    Column {
                                        Text(
                                            text = "KaamAI",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Your workday, made simpler.",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        },
                        navigationIcon = {
                            if (currentRoute == KaamAIScreen.NewVisit.route) {
                                IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            } else {
                                IconButton(
                                    onClick = {
                                        scope.launch { drawerState.open() }
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = "Open menu"
                                    )
                                }
                            }
                        },
                        actions = {
                            if (currentRoute == KaamAIScreen.Home.route || currentRoute == null) {
                                IconButton(
                                    onClick = {
                                        // Notifications
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Notifications,
                                        contentDescription = "Notifications"
                                    )
                                }
                            }
                        }
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        screens.forEach { screen ->
                            NavigationBarItem(
                                selected = currentRoute == screen.route,
                                onClick = {
                                    if (currentRoute != screen.route) {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                },
                                icon = { Icon(screen.icon, contentDescription = screen.title) },
                                label = { Text(screen.title) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = KaamAIScreen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(KaamAIScreen.Home.route) {
                    HomeScreen(
                        viewModel = viewModel,
                        onStartNewVisit = {
                            navController.navigate(KaamAIScreen.NewVisit.route)
                        }
                    )
                }
                composable(KaamAIScreen.Reports.route) {
                    ReportsScreen(
                        onHomeClick = {
                            navController.navigate(KaamAIScreen.Home.route) {
                                popUpTo(KaamAIScreen.Home.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(KaamAIScreen.NewVisit.route) {
                    NewVisitScreen(
                        onBackClick = { navController.popBackStack() },
                        onSaveClick = { customer, notes ->
                            viewModel.addReport(customer, notes)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
