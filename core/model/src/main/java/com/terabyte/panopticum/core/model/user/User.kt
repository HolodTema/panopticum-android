package com.terabyte.panopticum.core.model.user

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val createdAtMills: Long
)