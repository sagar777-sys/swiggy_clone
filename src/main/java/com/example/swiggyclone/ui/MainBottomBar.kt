package com.example.swiggyclone.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomBar(navController: NavController) {
    // This tells us which screen we are currently on so we can highlight the right icon
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {
        // 1. Home Button
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = {
                if (currentRoute != "home") {
                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                }
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF60B246)) // Swiggy Green
        )

        // 2. Search Button
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = currentRoute == "search",
            onClick = {
                if (currentRoute != "search") navController.navigate("search")
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF60B246))
        )

        // 3. Account Button
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Account") },
            label = { Text("Account") },
            selected = currentRoute == "account",
            onClick = {
                if (currentRoute != "account") navController.navigate("account")
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF60B246))
        )
    }
}