package com.terabyte.panopticum.core.model.location

import kotlinx.serialization.Serializable

@Serializable
sealed class LocationState {
    data object Inactive: LocationState()
    data object Active: LocationState()
}
