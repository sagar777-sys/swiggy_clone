package com.example.swiggyclone.data

// This is the blueprint for the food they sell
data class MenuItem(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val imageUrl: String
)