package com.example.weatherappproject.presentation.model


data class WeatherState(
    val weatherInfo: WeatherInfoPresentation? = null,
    val isLoading: Boolean = false
)
