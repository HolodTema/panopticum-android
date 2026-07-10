package com.terabyte.panopticum.core.model.auth

data class LoginRequest(
    val email: String,
    val password: String
)
