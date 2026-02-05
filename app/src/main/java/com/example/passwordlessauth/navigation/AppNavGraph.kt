package com.example.passwordlessauth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordlessauth.ui.login.LoginScreen
import com.example.passwordlessauth.ui.otp.OtpScreen
import com.example.passwordlessauth.ui.session.SessionScreen
import com.example.passwordlessauth.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(viewModel: AuthViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("otp") {
            OtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("session") {
            SessionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
