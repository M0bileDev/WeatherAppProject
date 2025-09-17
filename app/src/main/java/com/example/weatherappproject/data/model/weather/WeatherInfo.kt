package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherInfo

data class WeatherInfo(
    override val weatherDataPerDay: Map<Int, List<WeatherData>>,
    override val currentWeather: WeatherData?,
) : WeatherInfo(
    weatherDataPerDay,
    currentWeather
)
