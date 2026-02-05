package com.example.passwordlessauth.analytics

import timber.log.Timber

class TimberLogger : AnalyticsLogger {
    override fun logOtpGenerated(email: String) {
        Timber.d("OTP generated for $email")
    }

    override fun logOtpSuccess(email: String) {
        Timber.d("OTP success for $email")
    }

    override fun logOtpFailure(email: String) {
        Timber.e("OTP failed for $email")
    }

    override fun logLogout() {
        Timber.d("User logged out")
    }
}
