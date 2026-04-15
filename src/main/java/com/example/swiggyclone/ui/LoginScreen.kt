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
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Tracks if the user failed the format rules
    var inputError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Swiggy Clone", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF60B246))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Login to your account", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = emailOrPhone,
            onValueChange = {
                emailOrPhone = it
                inputError = false
            },
            label = { Text("Gmail or 10-digit Phone") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = inputError,
            supportingText = { if (inputError) Text("Enter a valid @gmail.com or 10-digit number") }
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

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Smart rules check if they typed a valid email OR a valid phone number
                val isEmail = emailOrPhone.endsWith("@gmail.com", ignoreCase = true)
                val isPhone = emailOrPhone.length == 10 && emailOrPhone.all { it.isDigit() }

                val isValid = isEmail || isPhone

                inputError = !isValid

                if (isValid && password.isNotBlank()) {
                    // Save whichever data they used to log in
                    val extractedName = if (isEmail) emailOrPhone.substringBefore("@").replaceFirstChar { it.uppercase() } else "User"
                    val phoneToSave = if (isPhone) emailOrPhone else "+91 0000000000"
                    val emailToSave = if (isEmail) emailOrPhone else "user@phone.com"

                    authViewModel.login(name = extractedName, email = emailToSave, phone = phoneToSave)
                    onLoginSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60B246))
        ) {
            Text("LOGIN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.Gray)
            TextButton(onClick = { navController.navigate("signup") }) {
                Text("Sign Up", color = Color(0xFF60B246), fontWeight = FontWeight.Bold)
            }
        }
    }
}