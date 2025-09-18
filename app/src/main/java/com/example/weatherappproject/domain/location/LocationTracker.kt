package com.example.weatherappproject.domain.location

import com.example.weatherappproject.domain.model.location.LocationDomain

interface LocationTracker {
    suspend fun  getCurrentLocation() : LocationDomain?
}