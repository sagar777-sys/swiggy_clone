package com.example.swiggyclone.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swiggyclone.data.DummyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Swiggy Clone", fontWeight = FontWeight.Bold) }
            )
        },
        // 👇 Here is the magic line that adds your new Bottom Navigation! 👇
        bottomBar = {
            MainBottomBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Text("All Restaurants", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Looping through your specific DummyData.restaurants list
            items(DummyData.restaurants) { restaurant ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            // When clicked, navigate to this specific restaurant's menu
                            navController.navigate("restaurant/${restaurant.id}")
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = restaurant.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        // NOTE: If your Restaurant data class uses different variable names
                        // for rating/delivery time, just update these below!
                        Text(text = "⭐ ${restaurant.rating} • ${restaurant.deliveryTime}")
                    }
                }
            }
        }
    }
}