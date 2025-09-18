package com.example.weatherappproject.domain.repository

import com.example.weatherappproject.utils.Resource
import com.example.weatherappproject.domain.model.weather.WeatherInfoDomain

interface WeatherRepository<T: WeatherInfoDomain> {
    suspend fun getWeather(lat: Double, long: Double): Resource<T>
}