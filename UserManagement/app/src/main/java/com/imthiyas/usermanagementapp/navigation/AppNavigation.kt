package com.imthiyas.usermanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imthiyas.usermanagementapp.ui.screens.HomeScreen
import com.imthiyas.usermanagementapp.ui.screens.ProductDetailsScreen
import com.imthiyas.usermanagementapp.ui.screens.ProductsScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()



    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onProductsClick = { navController.navigate("products") })
        }


        composable("products") {
            ProductsScreen(
                onProductClick = { productId ->
                    navController.navigate("product_details/$productId")
                }, onBackClick = { navController.popBackStack() }
            )
        }

        composable("product_details/{productId}") { backStackEntry ->

            val productId = backStackEntry
                .arguments
                ?.getString("productId")

            ProductDetailsScreen(
                productId = productId
            )
        }


    }
}