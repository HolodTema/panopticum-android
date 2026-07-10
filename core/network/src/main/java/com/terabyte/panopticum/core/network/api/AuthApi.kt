package com.terabyte.panopticum.core.network.api

import com.terabyte.panopticum.core.model.auth.AuthResponse
import com.terabyte.panopticum.core.model.auth.LoginRequest
import com.terabyte.panopticum.core.model.auth.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("api/auth/logout")
    suspend fun logout(): Response<Unit>
}
