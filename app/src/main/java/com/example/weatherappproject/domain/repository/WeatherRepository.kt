package com.example.weatherappproject.domain.repository

import com.example.weatherappproject.domain.model.utils.Resource
import com.example.weatherappproject.domain.model.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(lat: Double, long: Double): Resource<WeatherInfo>
}