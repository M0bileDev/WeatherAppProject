package com.example.weatherappproject.data.location

import com.example.weatherappproject.domain.model.location.LocationDomain

data class LocationData(
    override val latitude: Double,
    override val longitude: Double
) : LocationDomain(latitude, longitude)
