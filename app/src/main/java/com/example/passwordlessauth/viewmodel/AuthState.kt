package com.example.passwordlessauth.viewmodel

data class AuthState(
    val email: String = "",
    val isOtpSent: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
    val sessionStartTime: Long? = null
)
