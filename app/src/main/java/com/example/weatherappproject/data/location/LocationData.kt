package com.example.weatherappproject.data.location

import com.example.weatherappproject.domain.model.location.LocationData

data class LocationData(
    override val latitude: Double,
    override val longitude: Double
) : LocationData(latitude, longitude)
