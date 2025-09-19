package com.example.weatherappproject.presentation.mapper

import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.presentation.model.WeatherPresentation
import com.example.weatherappproject.presentation.model.WeatherInfoPresentation
import com.example.weatherappproject.presentation.model.WeatherType.Companion.fromWMO
import javax.inject.Inject

class WeatherMapperPresentationImpl @Inject constructor() : WeatherMapperPresentation {
    override fun WeatherInfoData.toWeatherDataPresentation(): WeatherInfoPresentation {

        val weatherDataPerDay = weatherPerDay.mapValues {
            it.value.map { weatherData ->
                WeatherPresentation(
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
            WeatherPresentation(
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
}

