package com.example.weatherappproject.presentation.model

sealed interface WeatherViewModelAction {
    object NoLocationData : WeatherViewModelAction
    data class ApiError(val message: String? = null) : WeatherViewModelAction
}