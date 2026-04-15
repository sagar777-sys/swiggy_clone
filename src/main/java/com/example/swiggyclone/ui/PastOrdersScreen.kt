package com.example.swiggyclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// A simple data structure just for this screen to hold our dummy orders
data class PastOrder(
    val id: String,
    val restaurantName: String,
    val date: String,
    val items: String,
    val totalAmount: Double,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastOrdersScreen(navController: NavController) {
    // 1. Our dummy list of past orders
    val pastOrders = listOf(
        PastOrder("ORD-1029", "Domino's Pizza", "07 Apr 2026, 08:30 PM", "1 x Margherita Pizza, 1 x Pepperoni Pizza", 600.0, "Delivered"),
        PastOrder("ORD-0984", "Burger King", "05 Apr 2026, 01:15 PM", "2 x Crispy Veg Burger, 1 x Medium Fries", 239.0, "Delivered"),
        PastOrder("ORD-0871", "Amar Juice Centre", "02 Apr 2026, 05:45 PM", "2 x Pav Bhaji, 2 x Mosambi Juice", 460.0, "Delivered")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Past Orders", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    // A back button to return to the Account screen
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(pastOrders) { order ->
                PastOrderCard(order)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun PastOrderCard(order: PastOrder) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(order.restaurantName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("₹${order.totalAmount}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(order.items, color = Color.DarkGray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(order.date, color = Color.Gray, fontSize = 12.sp)

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                // A nice green checkmark for delivered items
                Icon(Icons.Default.CheckCircle, contentDescription = "Delivered", tint = Color(0xFF60B246), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(order.status, color = Color.DarkGray, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.weight(1f))

                // Reorder button (Currently just for looks!)
                OutlinedButton(
                    onClick = { /* In the future, this would add these items to the cart! */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF60B246)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF60B246)),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("REORDER", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}