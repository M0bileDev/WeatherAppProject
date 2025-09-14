package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherInfo

data class WeatherInfo(
    val weatherDataPerDayData: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
) : WeatherInfo(
    weatherDataPerDayData,
    currentWeatherData
)
