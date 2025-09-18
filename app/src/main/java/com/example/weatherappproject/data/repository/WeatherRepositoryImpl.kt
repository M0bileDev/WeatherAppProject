package com.example.weatherappproject.data.repository

import com.example.weatherappproject.data.mappers.toWeatherInfo
import com.example.weatherappproject.data.model.weather.WeatherInfo
import com.example.weatherappproject.data.remote.api.WeatherApi
import com.example.weatherappproject.utils.Resource
import com.example.weatherappproject.domain.model.weather.WeatherInfoDomain
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : DefaultWeatherRepository {
    override suspend fun getWeather(
        lat: Double,
        long: Double
    ): Resource<WeatherInfo> {
        return try {
            val data = weatherApi.getWeather(lat, long).run { toWeatherInfo() }
            Resource.Success(data)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "An unknown error occurred")
        }
    }
}