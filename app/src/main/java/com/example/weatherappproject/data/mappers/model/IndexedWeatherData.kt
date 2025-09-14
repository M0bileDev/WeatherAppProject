package com.example.weatherappproject.data.mappers.model

import com.example.weatherappproject.data.model.weather.WeatherData


data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)
