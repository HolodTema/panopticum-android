package com.terabyte.panopticum.core.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)

