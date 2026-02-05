package com.example.passwordlessauth.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordlessauth.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SessionScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val sessionStartTime = remember {
        System.currentTimeMillis()
    }

    var elapsedTime by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            elapsedTime = System.currentTimeMillis() - sessionStartTime
        }
    }

    val startTimeFormatted = remember {
        SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .format(Date(sessionStartTime))
    }

    val minutes = (elapsedTime / 1000) / 60
    val seconds = (elapsedTime / 1000) % 60
    val durationFormatted = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Session Active",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Session Started At",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = startTimeFormatted,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Session Duration",
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = durationFormatted,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color(0xFF4CAF50)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                Timber.tag("OTP_AUTH").i("User logged out - Session duration: $durationFormatted")
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout", fontSize = 16.sp)
        }
    }
}