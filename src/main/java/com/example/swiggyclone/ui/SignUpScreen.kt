package com.example.swiggyclone.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swiggyclone.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onSignUpSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Error trackers to trigger the red text
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create Account", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF60B246))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Sign up to get started", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false // Hide error when user is typing
            },
            label = { Text("Gmail Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = emailError,
            supportingText = { if (emailError) Text("Must end with @gmail.com") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { input ->
                // Only allow numbers, and block typing past 10 digits
                if (input.length <= 10 && input.all { it.isDigit() }) {
                    phone = input
                    phoneError = false
                }
            },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = phoneError,
            supportingText = { if (phoneError) Text("Must be exactly 10 digits") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validation Rules
                val isEmailValid = email.endsWith("@gmail.com", ignoreCase = true)
                val isPhoneValid = phone.length == 10

                emailError = !isEmailValid
                phoneError = !isPhoneValid

                // If everything is perfectly valid, create the user!
                if (name.isNotBlank() && isEmailValid && isPhoneValid && password.isNotBlank()) {
                    authViewModel.login(name = name, email = email, phone = phone)
                    onSignUpSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60B246))
        ) {
            Text("SIGN UP", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account?", color = Color.Gray)
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Log In", color = Color(0xFF60B246), fontWeight = FontWeight.Bold)
            }
        }
    }
}