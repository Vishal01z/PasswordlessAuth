package com.example.passwordlessauth.analytics

interface AnalyticsLogger {
    fun logOtpGenerated(email: String)
    fun logOtpSuccess(email: String)
    fun logOtpFailure(email: String)
    fun logLogout()
}
