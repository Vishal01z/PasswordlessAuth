package com.example.passwordlessauth.ui.otp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordlessauth.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    var otp by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf(60L) }
    var attemptsLeft by remember { mutableStateOf(3) }

    // Check if email is empty and navigate back if needed
    LaunchedEffect(Unit) {
        if (viewModel.currentEmail.isEmpty()) {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
            return@LaunchedEffect
        }
    }

    // Live timer
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            remainingTime = viewModel.remainingTime() / 1000
            attemptsLeft = viewModel.attemptsLeft()

            if (remainingTime <= 0) {
                message = "OTP expired. Please resend."
                break
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enter OTP",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "OTP sent to ${viewModel.currentEmail}",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Timer display
        Text(
            text = "Time remaining: ${remainingTime}s",
            fontSize = 18.sp,
            color = if (remainingTime <= 10) Color.Red else Color.Black,
            fontWeight = if (remainingTime <= 10) FontWeight.Bold else FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Attempts left: $attemptsLeft",
            fontSize = 16.sp,
            color = if (attemptsLeft <= 1) Color.Red else Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = {
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otp = it
                    message = ""
                }
            },
            label = { Text("Enter 6-digit OTP") },
            singleLine = true,
            isError = message.isNotEmpty() && message.contains("Invalid")
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val success = viewModel.verifyOtp(otp)
                if (success) {
                    navController.navigate("session") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    if (remainingTime <= 0) {
                        message = "OTP expired"
                    } else if (attemptsLeft <= 0) {
                        message = "Max attempts reached. Please resend OTP."
                    } else {
                        message = "Invalid OTP. ${viewModel.attemptsLeft()} attempts left."
                    }
                    otp = ""
                }
            },
            enabled = otp.length == 6 && attemptsLeft > 0 && remainingTime > 0
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend OTP button
        TextButton(
            onClick = {
                viewModel.resendOtp()
                otp = ""
                message = "New OTP sent!"
                remainingTime = 60
                attemptsLeft = 3
            }
        ) {
            Text("Resend OTP")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = message,
                color = if (message.contains("sent")) Color.Green else Color.Red,
                fontSize = 14.sp
            )
        }
    }
}