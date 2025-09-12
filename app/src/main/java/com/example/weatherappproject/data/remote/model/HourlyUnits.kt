package com.example.weatherappproject.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnits(
    @param:Json(name = "pressure_msl")
    val pressureMsl: String,
    @param:Json(name = "relativehumidity_2m")
    val relativeHumidity2m: String,
    @param:Json(name = "temperature_2m")
    val temperature2m: String,
    @param:Json(name = "time")
    val time: String,
    @param:Json(name = "weathercode")
    val weatherCode: String,
    @param:Json(name = "windspeed_10m")
    val windSpeed10m: String
)