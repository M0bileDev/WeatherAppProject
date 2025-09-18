package com.example.weatherappproject.domain.model.weather

import java.time.LocalDateTime

abstract class WeatherDomain(
    open val time: LocalDateTime,
    open val temperatureCelsius: Double,
    open val pressure: Double,
    open val windSpeed: Double,
    open val humidity: Int,
    open val weatherCode: Int
)
