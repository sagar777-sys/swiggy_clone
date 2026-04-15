package com.example.swiggyclone.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swiggyclone.data.DummyData
import com.example.swiggyclone.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val cartItems by cartViewModel.cartItems.collectAsState()

    // 👇 ADDED: We need to know the totals to show the green cart bar! 👇
    val totalItems = cartItems.sumOf { it.quantity }
    val totalPrice = cartViewModel.getCartTotal()

    val allFoodItemsAndRestaurants = remember {
        DummyData.restaurants.flatMap { restaurant ->
            DummyData.getMenuItems(restaurant.id).map { menuItem ->
                Pair(menuItem, restaurant)
            }
        }
    }

    val searchResults = if (searchQuery.isBlank()) {
        emptyList()
    } else {
        allFoodItemsAndRestaurants.filter { pair ->
            pair.first.name.contains(searchQuery, ignoreCase = true) ||
                    pair.first.description.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Search", fontWeight = FontWeight.Bold) }) },
        bottomBar = { MainBottomBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search for dishes...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF60B246)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (searchQuery.isNotBlank() && searchResults.isEmpty()) {
                Text("No results found for \"$searchQuery\"", color = Color.Gray)
            } else {
                // Notice the weight(1f) here. It pushes anything below it to the bottom!
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(
                        items = searchResults,
                        key = { pair -> pair.first.id }
                    ) { pair ->

                        val item = pair.first
                        val restaurant = pair.second

                        val cartItem = cartItems.find { it.menuItem.id == item.id }
                        val quantity = cartItem?.quantity ?: 0

                        MenuItemCard(
                            item = item,
                            quantity = quantity,
                            onIncrement = { cartViewModel.addToCart(item, restaurant.name) },
                            onDecrement = { cartViewModel.removeFromCart(item) }
                        )
                    }
                }
            }

            // 👇 ADDED: The Green View Cart Bar! 👇
            // It will sit perfectly above your bottom navigation tabs.
            if (totalItems > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                CartBottomBar(
                    itemCount = totalItems,
                    totalPrice = totalPrice,
                    onViewCartClick = { navController.navigate("checkout") }
                )
            }
        }
    }
}