package com.example.swiggyclone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.swiggyclone.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit,
    onOrderSuccess: () -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalAmount = cartViewModel.getCartTotal()

    val paymentOptions = listOf(
        "UPI (Google Pay, PhonePe, Paytm)",
        "Credit / Debit Card",
        "Net Banking",
        "Cash on Delivery (COD)"
    )

    var selectedPaymentMethod by remember { mutableStateOf(paymentOptions[0]) }
    var showSuccessPopup by remember { mutableStateOf(false) }

    // --- SUCCESS POPUP DIALOG ---
    if (showSuccessPopup) {
        Dialog(onDismissRequest = { /* Empty so they cannot click outside to close it */ }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        tint = Color(0xFF60B246),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Order Placed Successfully!", fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Thank you for choosing us. Your food is being prepared!", color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            showSuccessPopup = false
                            cartViewModel.clearCart()
                            onOrderSuccess()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60B246)),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("BACK TO HOME", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("TOTAL", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        Text("₹$totalAmount", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                    }

                    Button(
                        onClick = { showSuccessPopup = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60B246)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(50.dp).width(150.dp)
                    ) {
                        Text("PAY ₹$totalAmount", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // --- YOUR ORDER RECEIPT ---
            Text(
                text = "YOUR ORDER",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    cartItems.forEach { cartItem ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${cartItem.menuItem.name}  x ${cartItem.quantity}",
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "₹${cartItem.menuItem.price * cartItem.quantity}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Item Total", fontWeight = FontWeight.Bold)
                        Text("₹$totalAmount", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- PAYMENT OPTIONS ---
            Text(
                text = "PAYMENT OPTIONS",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column {
                    paymentOptions.forEachIndexed { index, method ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedPaymentMethod = method }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (selectedPaymentMethod == method),
                                onClick = { selectedPaymentMethod = method },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF60B246))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = method, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        }

                        if (index < paymentOptions.size - 1) {
                            HorizontalDivider(color = Color(0xFFEEEEEE))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}