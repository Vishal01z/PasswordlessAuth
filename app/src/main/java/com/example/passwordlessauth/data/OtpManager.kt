package com.example.passwordlessauth.data

import timber.log.Timber

object OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()

        otpStore[email] = OtpData(
            otp = otp,
            expiryTime = System.currentTimeMillis() + 60_000,
            attemptsLeft = 3
        )

        Timber.tag("OTP_AUTH").d("OTP generated for $email: $otp")
        return otp
    }

    fun verifyOtp(email: String, inputOtp: String): Boolean {
        val data = otpStore[email]

        if (data == null) {
            Timber.tag("OTP_AUTH").e("OTP validation failure - No OTP found for email")
            return false
        }

        // expiry check
        if (System.currentTimeMillis() > data.expiryTime) {
            Timber.tag("OTP_AUTH").e("OTP validation failure - OTP expired")
            return false
        }

        // attempt check
        if (data.attemptsLeft <= 0) {
            Timber.tag("OTP_AUTH").e("OTP validation failure - Max attempts reached")
            return false
        }

        return if (data.otp == inputOtp) {
            Timber.tag("OTP_AUTH").i("OTP validation success for $email")
            true
        } else {
            data.attemptsLeft--
            Timber.tag("OTP_AUTH").e("OTP validation failure - Incorrect OTP. Attempts left: ${data.attemptsLeft}")
            false
        }
    }

    fun getAttemptsLeft(email: String): Int {
        return otpStore[email]?.attemptsLeft ?: 0
    }

    fun getRemainingTime(email: String): Long {
        val data = otpStore[email] ?: return 0
        return (data.expiryTime - System.currentTimeMillis()).coerceAtLeast(0)
    }

    fun isOtpExpired(email: String): Boolean {
        val data = otpStore[email] ?: return true
        return System.currentTimeMillis() > data.expiryTime
    }
}