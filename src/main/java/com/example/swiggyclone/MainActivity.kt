package com.example.swiggyclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.swiggyclone.ui.*
import com.example.swiggyclone.ui.theme.SwiggyCloneTheme
import com.example.swiggyclone.viewmodel.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwiggyCloneTheme {
                // We use our new navigation map here!
                AppNavigation()
            }
        }
    }
}

@Composable
fun SwiggyApp(cartViewModel: CartViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, padding = innerPadding, cartViewModel = cartViewModel)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Search", "search", Icons.Default.Search),
        BottomNavItem("Cart", "cart", Icons.Default.ShoppingCart),
        BottomNavItem("Account", "profile", Icons.Default.Person)
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val route: String, val icon: ImageVector)

@Composable
fun NavigationGraph(navController: NavHostController, padding: PaddingValues, cartViewModel: CartViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(padding)
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }

        // --- NEW ROUTE FOR THE MENU ---
        composable(
            route = "restaurant/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extract the ID from the click, or default to 1 if something goes wrong
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 1
            RestaurantScreen(navController, restaurantId, cartViewModel)
        }
        // ------------------------------

        composable("search") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("🔍 Search Screen (Coming Soon)")
            }
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
        composable("cart") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("🛒 Cart Screen (Coming Soon)")
            }
        }
        composable("profile") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("👤 Profile Screen ")
            }
        }
    }
}
