package com.example.weatherappproject.domain.location

import com.example.weatherappproject.domain.model.location.LocationData

interface LocationTracker {
    suspend fun  getCurrentLocation() : LocationData?
}