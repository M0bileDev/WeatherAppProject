package com.example.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @param:Json(name = "pressure_msl")
    val pressureMsl: List<Double>,
    @param:Json(name = "relativehumidity_2m")
    val relativeHumidity2m: List<Int>,
    @param:Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    @param:Json(name = "time")
    val time: List<String>,
    @param:Json(name = "weathercode")
    val weatherCode: List<Int>,
    @param:Json(name = "windspeed_10m")
    val windSpeed10m: List<Double>
)