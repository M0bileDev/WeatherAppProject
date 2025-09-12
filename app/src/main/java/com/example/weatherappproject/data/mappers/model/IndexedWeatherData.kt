package com.example.weatherappproject.data.mappers.model

import com.example.weatherappproject.domain.weather.WeatherData

data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)
