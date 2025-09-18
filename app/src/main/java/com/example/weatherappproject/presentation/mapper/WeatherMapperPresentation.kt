package com.example.weatherappproject.presentation.mapper

import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.presentation.model.WeatherInfoPresentation

interface WeatherMapperPresentation {
    fun WeatherInfoData.toWeatherDataPresentation(): WeatherInfoPresentation
}