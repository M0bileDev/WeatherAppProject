package com.example.weatherappproject.data.repository

import com.example.weatherappproject.data.model.weather.WeatherInfo
import com.example.weatherappproject.domain.repository.WeatherRepository

interface DefaultWeatherRepository : WeatherRepository<WeatherInfo>