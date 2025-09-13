package com.example.weatherappproject.domain.location

import com.example.weatherappproject.domain.location.model.LocationData

interface LocationTracker {
    suspend fun  getCurrentLocation() : LocationData?
}