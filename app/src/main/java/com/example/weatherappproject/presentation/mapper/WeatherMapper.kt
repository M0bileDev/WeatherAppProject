package com.example.weatherappproject.presentation.mapper

import com.example.weatherappproject.data.model.weather.WeatherInfo
import com.example.weatherappproject.presentation.model.WeatherDataPresentation
import com.example.weatherappproject.presentation.model.WeatherInfoPresentation
import com.example.weatherappproject.presentation.model.WeatherType.Companion.fromWMO

fun WeatherInfo.toWeatherDataPresentation(): WeatherInfoPresentation {

    val weatherDataPerDay = weatherDataPerDayData.mapValues {
        it.value.map { weatherData ->
            WeatherDataPresentation(
                weatherData.timeData,
                weatherData.temperatureCelsiusData,
                weatherData.pressureData,
                weatherData.windSpeedData,
                weatherData.humidityData,
                fromWMO(weatherData.weatherCodeData)
            )
        }
    }

    val weatherData = currentWeatherData?.let {
        WeatherDataPresentation(
            it.timeData,
            it.temperatureCelsiusData,
            it.pressureData,
            it.windSpeedData,
            it.humidityData,
            fromWMO(it.weatherCodeData)
        )
    }

    return WeatherInfoPresentation(
        weatherDataPerDay,
        weatherData
    )
}