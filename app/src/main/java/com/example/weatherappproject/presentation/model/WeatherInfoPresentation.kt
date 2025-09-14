package com.example.weatherappproject.presentation.model


data class WeatherInfoPresentation(
    val weatherDataPerDayData: Map<Int, List<WeatherDataPresentation>>,
    val currentWeatherData: WeatherDataPresentation?,
)
