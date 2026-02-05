package com.example.passwordlessauth.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordlessauth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Passwordless Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Enter your email to receive OTP",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = ""
            },
            label = { Text("Email") },
            isError = errorMessage.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                if (!isValidEmail(email)) {
                    errorMessage = "Please enter a valid email address"
                } else {
                    viewModel.sendOtp(email)
                    navController.navigate("otp")
                }
            },
            enabled = email.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP", fontSize = 16.sp)
        }
    }
}