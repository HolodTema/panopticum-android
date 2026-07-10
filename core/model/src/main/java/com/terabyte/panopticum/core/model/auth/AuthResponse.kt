package com.terabyte.panopticum.core.model.auth

data class AuthResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)

