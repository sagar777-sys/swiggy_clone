package com.example.swiggyclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swiggyclone.data.User
import com.example.swiggyclone.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onLogoutClick: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Account", fontWeight = FontWeight.Bold) }
            )
        },
        bottomBar = { MainBottomBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {

            item {
                if (currentUser != null) {
                    UserProfileCard(user = currentUser!!)
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                AccountOptionRow(
                    icon = Icons.Default.ShoppingCart,
                    title = "Past Orders",
                    subtitle = "Check your order history",
                    // 👇 THIS IS THE NEW NAVIGATION LINK 👇
                    onClick = { navController.navigate("past_orders") }
                )
                HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)

                AccountOptionRow(
                    icon = Icons.Default.LocationOn,
                    title = "Addresses",
                    subtitle = "Manage your saved addresses",
                    onClick = { /* TODO: Navigate to Addresses */ }
                )
                HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)

                AccountOptionRow(
                    icon = Icons.Default.Settings,
                    title = "Settings",
                    subtitle = "Notifications, permissions, etc.",
                    onClick = { /* TODO: Navigate to Settings */ }
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            authViewModel.logout()
                            onLogoutClick()
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = androidx.compose.ui.graphics.RectangleShape
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Log Out", tint = Color.Red)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Log Out", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = androidx.compose.ui.graphics.RectangleShape
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = "Profile Picture", modifier = Modifier.size(40.dp), tint = Color.Gray)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = user.phone, color = Color.Gray, fontSize = 14.sp)
                Text(text = user.email, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun AccountOptionRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = androidx.compose.ui.graphics.RectangleShape
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = Color.DarkGray)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(subtitle, color = Color.Gray, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(">", color = Color.Gray, fontWeight = FontWeight.Bold)
        }
    }
}