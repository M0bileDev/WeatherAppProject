package com.example.weatherappproject.data.mappers

import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.data.remote.model.WeatherDto

interface WeatherMapper {
    fun WeatherDto.toWeatherInfo(): WeatherInfoData
}