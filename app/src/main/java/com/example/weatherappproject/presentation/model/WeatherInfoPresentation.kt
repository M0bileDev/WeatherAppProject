package com.example.weatherappproject.presentation.model


data class WeatherInfoPresentation(
    val weatherPerDay: Map<Int, List<WeatherPresentation>>,
    val currentWeather: WeatherPresentation?,
)
