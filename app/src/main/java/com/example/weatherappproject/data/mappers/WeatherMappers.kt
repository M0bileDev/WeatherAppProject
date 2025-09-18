package com.example.weatherappproject.data.mappers

import com.example.weatherappproject.data.mappers.model.IndexedWeatherData
import com.example.weatherappproject.data.model.weather.WeatherData
import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.data.remote.model.Hourly
import com.example.weatherappproject.data.remote.model.WeatherDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_DATE_TIME

//Mappers from dto to domain model

fun Hourly.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, string ->
        val temperature = temperature2m[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed10m[index]
        val pressure = pressureMsl[index]
        val humidity = relativeHumidity2m[index]

        val weatherData = WeatherData(
            time = LocalDateTime.parse(string, ISO_DATE_TIME),
            temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherCode = weatherCode
        )
        //Indexed from 0 to 167 as hour from 0 to 23, for next days (max 7 days ahead)
        IndexedWeatherData(index, weatherData = weatherData)
    }
        //Want to group WeatherData for each day -> index 0 means current day [hours: 0, 1, 2 ,3...,],
        // next day [hours: 0, 1, 2 ,3...,] etc day+n (n<7)
        .groupBy {
            it.index % 7
        }
        //Indexed with IndexedWeatherData but WeatherData needed so extract WeatherData from IndexedWeatherData
        .mapValues { values ->
            values.value.map { it.weatherData }
        }

}

fun WeatherDto.toWeatherInfo(): WeatherInfoData {
    val weatherDataMap = hourly.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find { weatherData ->
        weatherData.time.hour == now.hour
    }
    return WeatherInfoData(
        weatherPerDay = weatherDataMap,
        currentWeather = currentWeatherData
    )
}

