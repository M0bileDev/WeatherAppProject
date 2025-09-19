package com.example.weatherappproject.presentation.model

import java.time.LocalDateTime


data class WeatherPresentation(
    val timeData: LocalDateTime,
    val temperatureCelsiusData: Double,
    val pressureData: Double,
    val windSpeedData: Double,
    val humidityData: Int,
    val weatherType: WeatherType
)

