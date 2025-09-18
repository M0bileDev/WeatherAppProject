package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherInfoDomain

data class WeatherInfoData(
    override val weatherPerDay: Map<Int, List<WeatherData>>,
    override val currentWeather: WeatherData?,
) : WeatherInfoDomain(
    weatherPerDay,
    currentWeather
)
