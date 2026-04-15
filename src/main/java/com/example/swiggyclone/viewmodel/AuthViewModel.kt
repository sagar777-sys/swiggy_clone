package com.example.swiggyclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.swiggyclone.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    // Now securely accepts the real phone number
    fun login(name: String, email: String, phone: String = "+91 0000000000") {
        _currentUser.value = User(name = name, email = email, phone = phone)
    }

    fun logout() {
        _currentUser.value = null
    }
}