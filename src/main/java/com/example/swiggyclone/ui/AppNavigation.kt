package com.example.swiggyclone.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swiggyclone.viewmodel.AuthViewModel
import com.example.swiggyclone.viewmodel.CartViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val cartViewModel: CartViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("signup") {
            SignUpScreen(
                navController = navController,
                authViewModel = authViewModel,
                onSignUpSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("search") {
            SearchScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }

        composable("account") {
            AccountScreen(
                navController = navController,
                authViewModel = authViewModel,
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            )
        }

        composable("restaurant/{restaurantId}") { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId")?.toIntOrNull() ?: 1

            RestaurantScreen(
                navController = navController,
                restaurantId = restaurantId,
                cartViewModel = cartViewModel
            )
        }

        composable("checkout") {
            CheckoutScreen(
                cartViewModel = cartViewModel,
                onBackClick = { navController.popBackStack() },
                onOrderSuccess = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // 👇 THE NEW PAST ORDERS ROUTE 👇
        composable("past_orders") {
            PastOrdersScreen(navController = navController)
        }
    }
}