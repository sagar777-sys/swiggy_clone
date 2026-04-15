
package com.example.swiggyclone.data
// This is the blueprint for a restaurant
data class Restaurant(
    val id: Int,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val deliveryTime: String,
    val imageUrl: String,
    val menu: List<MenuItem> = emptyList() // Added menu property
)