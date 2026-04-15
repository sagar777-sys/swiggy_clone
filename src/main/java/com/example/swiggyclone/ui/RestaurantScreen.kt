package com.example.swiggyclone.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.swiggyclone.data.DummyData
import com.example.swiggyclone.data.MenuItem
import com.example.swiggyclone.viewmodel.CartViewModel

@Composable
fun RestaurantScreen(
    navController: NavHostController,
    restaurantId: Int,
    cartViewModel: CartViewModel
) {
    // Look up the specific restaurant and its food using the ID
    val restaurant = DummyData.restaurants.find { it.id == restaurantId }
    val menuItems = DummyData.getMenuItems(restaurantId)

    // Collect cart state
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalItems = cartItems.sumOf { it.quantity }
    val totalPrice = cartViewModel.getCartTotal()

    if (restaurant != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header: Restaurant Name with a Back Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = restaurant.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                HorizontalDivider()

                // List of food items
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    // The 'key' ensures Android only redraws the exact item that changes!
                    items(
                        items = menuItems,
                        key = { item -> item.id }
                    ) { item ->

                        // Check the cart to see how many of this specific item the user has
                        val cartItem = cartItems.find { it.menuItem.id == item.id }
                        val quantity = cartItem?.quantity ?: 0

                        MenuItemCard(
                            item = item,
                            quantity = quantity,
                            onIncrement = { cartViewModel.addToCart(item, restaurant.name) }, // Fixed: Pass restaurant.name
                            onDecrement = { cartViewModel.removeFromCart(item) }
                        )
                    }
                }
            }

            // Show CartBottomBar if there are items in the cart
            if (totalItems > 0) {
                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    CartBottomBar(
                        itemCount = totalItems,
                        totalPrice = totalPrice,
                        onViewCartClick = { navController.navigate("checkout") }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItem,
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "₹${item.price}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // The Quantity Logic
                if (quantity == 0) {
                    // Show standard ADD button if none in cart
                    OutlinedButton(
                        onClick = onIncrement,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF60B246)),
                        border = BorderStroke(1.dp, Color(0xFF60B246))
                    ) {
                        Text("ADD", fontWeight = FontWeight.Bold)
                    }
                } else {
                    // Show the - / + quantity selector if 1 or more are in cart
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(0xFFE8F6E9), RoundedCornerShape(8.dp)) // Light green
                            .border(1.dp, Color(0xFF60B246), RoundedCornerShape(8.dp))
                    ) {
                        TextButton(onClick = onDecrement, modifier = Modifier.width(40.dp)) {
                            Text("-", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF60B246))
                        }

                        Text(text = quantity.toString(), fontWeight = FontWeight.Bold, color = Color(0xFF60B246))

                        TextButton(onClick = onIncrement, modifier = Modifier.width(40.dp)) {
                            Text("+", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF60B246))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartBottomBar(itemCount: Int, totalPrice: Double, onViewCartClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFF60B246), shape = RoundedCornerShape(8.dp)) // Swiggy Green
            .clickable { onViewCartClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "$itemCount ITEM${if (itemCount > 1) "S" else ""}",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "₹$totalPrice",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "View Cart ➔",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}