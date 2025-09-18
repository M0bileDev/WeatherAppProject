package com.example.weatherappproject.data.model.weather

import com.example.weatherappproject.domain.model.weather.WeatherInfoDomain

data class WeatherInfo(
    override val weatherDomainPerDay: Map<Int, List<WeatherData>>,
    override val currentWeather: WeatherData?,
) : WeatherInfoDomain(
    weatherDomainPerDay,
    currentWeather
)
