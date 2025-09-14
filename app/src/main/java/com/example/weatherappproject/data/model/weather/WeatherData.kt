package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherData
import java.time.LocalDateTime

data class WeatherData(
    val timeData: LocalDateTime,
    val temperatureCelsiusData: Double,
    val pressureData: Double,
    val windSpeedData: Double,
    val humidityData: Int,
    val weatherCodeData: Int
) : WeatherData(
    timeData,
    temperatureCelsiusData,
    pressureData,
    windSpeedData,
    humidityData,
    weatherCodeData
)
