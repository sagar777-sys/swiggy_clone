package com.example.swiggyclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.swiggyclone.data.CartItem
import com.example.swiggyclone.data.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(menuItem: MenuItem, restaurantName: String) {
        val currentList = _cartItems.value.toMutableList()
        val existingItem = currentList.find { it.menuItem.id == menuItem.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentList.add(CartItem(menuItem, restaurantName, 1))
        }
        _cartItems.value = currentList
    }

    fun removeFromCart(menuItem: MenuItem) {
        val currentList = _cartItems.value.toMutableList()
        val existingItem = currentList.find { it.menuItem.id == menuItem.id }

        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                currentList.remove(existingItem)
            }
        }
        _cartItems.value = currentList
    }

    fun getCartTotal(): Double {
        return _cartItems.value.sumOf { (it.menuItem.price * it.quantity).toDouble() }
    }
    fun clearCart() {
        _cartItems.value = emptyList()
    }
}