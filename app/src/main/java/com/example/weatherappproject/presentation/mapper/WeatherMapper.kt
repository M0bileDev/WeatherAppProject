package com.example.weatherappproject.presentation.mapper

import com.example.weatherappproject.data.model.weather.WeatherInfo
import com.example.weatherappproject.presentation.model.WeatherDataPresentation
import com.example.weatherappproject.presentation.model.WeatherInfoPresentation
import com.example.weatherappproject.presentation.model.WeatherType.Companion.fromWMO

fun WeatherInfo.toWeatherDataPresentation(): WeatherInfoPresentation {

    val weatherDataPerDay = weatherDomainPerDay.mapValues {
        it.value.map { weatherData ->
            WeatherDataPresentation(
                weatherData.time,
                weatherData.temperatureCelsius,
                weatherData.pressure,
                weatherData.windSpeed,
                weatherData.humidity,
                fromWMO(weatherData.weatherCode)
            )
        }.sortedBy { timeSort -> timeSort.timeData.hour }
    }

    val weatherData = currentWeather?.let {
        WeatherDataPresentation(
            it.time,
            it.temperatureCelsius,
            it.pressure,
            it.windSpeed,
            it.humidity,
            fromWMO(it.weatherCode)
        )
    }

    return WeatherInfoPresentation(
        weatherDataPerDay,
        weatherData
    )
}