package com.example.weatherappproject.data.repository

import com.example.weatherappproject.data.mappers.WeatherMapper
import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.data.remote.api.WeatherApi
import com.example.weatherappproject.utils.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherMapper: WeatherMapper
) : DefaultWeatherRepository {
    override suspend fun getWeather(
        lat: Double,
        long: Double
    ): Resource<WeatherInfoData> {
        return try {
            val data = weatherApi.getWeather(lat, long).run {
                with(weatherMapper) {
                    toWeatherInfo()
                }
            }
            Resource.Success(data)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "An unknown error occurred")
        }
    }
}