package com.example.weatherappproject.domain.repository

import com.example.weatherappproject.domain.util.Resource
import com.example.weatherappproject.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(lat: Double, long: Double): Resource<WeatherInfo>
}