package com.example.medilink.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val lastName: String,
    val userType: String,
    val email: String,
    val phone: String? = null,
    val age: Int? = null
)
