package com.terabyte.panopticum.core.model.location

sealed class LocationState {
    data object Inactive: LocationState()
    data object Active: LocationState()
}
