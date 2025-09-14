package com.example.weatherappproject.presentation.model

sealed interface WeatherViewModelAction {
    object NoLocationData : WeatherViewModelAction
    object ApiError : WeatherViewModelAction
}