package com.example.passwordlessauth.data

data class OtpData(
    val otp: String,
    val expiryTime: Long,
    var attemptsLeft: Int = 3
)
