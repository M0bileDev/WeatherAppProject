package com.example.weatherappproject.domain.location

import com.example.weatherappproject.domain.model.location.LocationDomain

interface LocationTracker<out T: LocationDomain> {
    suspend fun  getCurrentLocation() : T?
}