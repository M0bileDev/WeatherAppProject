package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherData
import java.time.LocalDateTime

data class WeatherData(
    override val time: LocalDateTime,
    override val temperatureCelsius: Double,
    override val pressure: Double,
    override val windSpeed: Double,
    override val humidity: Int,
    override val weatherCode: Int
) : WeatherData(
    time,
    temperatureCelsius,
    pressure,
    windSpeed,
    humidity,
    weatherCode
)
