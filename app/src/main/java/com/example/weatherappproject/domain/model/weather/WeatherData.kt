package com.example.weatherappproject.domain.model.weather

import java.time.LocalDateTime

abstract class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Int,
    val weatherCode: Int
)
