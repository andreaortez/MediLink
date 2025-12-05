package com.example.medilink.ui.login

data class LoggedInUserView(
    val userId: String,
    val displayName: String,
    val lastName: String,
    val userType: String,
    val email: String,
    val phone: String? = null,
    val age: Int? = null
)
