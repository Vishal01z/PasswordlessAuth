package com.example.passwordlessauth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.passwordlessauth.data.OtpManager

class AuthViewModel : ViewModel() {

    private val _currentEmail = mutableStateOf("")
    val currentEmail: String
        get() = _currentEmail.value

    fun sendOtp(email: String) {
        _currentEmail.value = email
        OtpManager.generateOtp(email)
    }

    fun verifyOtp(otp: String): Boolean {
        if (_currentEmail.value.isEmpty()) {
            return false
        }
        return OtpManager.verifyOtp(_currentEmail.value, otp)
    }

    fun attemptsLeft(): Int {
        if (_currentEmail.value.isEmpty()) {
            return 0
        }
        return OtpManager.getAttemptsLeft(_currentEmail.value)
    }

    fun remainingTime(): Long {
        if (_currentEmail.value.isEmpty()) {
            return 0
        }
        return OtpManager.getRemainingTime(_currentEmail.value)
    }

    fun resendOtp() {
        if (_currentEmail.value.isNotEmpty()) {
            OtpManager.generateOtp(_currentEmail.value)
        }
    }
}