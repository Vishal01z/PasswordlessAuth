package com.example.passwordlessauth.util

fun isValidEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".")
}
