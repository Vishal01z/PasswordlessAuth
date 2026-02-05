package com.example.passwordlessauth.viewmodel

sealed class AuthUiState {
    object LoggedOut : AuthUiState()
    object OtpSent : AuthUiState()
    object LoggedIn : AuthUiState()
}
