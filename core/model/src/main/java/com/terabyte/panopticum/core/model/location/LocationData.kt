package com.terabyte.panopticum.core.model.location

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long = System.currentTimeMillis()
)
