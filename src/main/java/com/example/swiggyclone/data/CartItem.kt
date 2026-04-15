package com.example.swiggyclone.data

// This is the blueprint for an item added to our shopping cart
data class CartItem(
    val menuItem: MenuItem,
    val restaurantName: String,
    var quantity: Int // This is a 'var' because the quantity can change!
)