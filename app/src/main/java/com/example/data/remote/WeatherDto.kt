package com.example.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.jetbrains.annotations.NotNull
import retrofit2.http.Field


@JsonClass(generateAdapter = true)
data class WeatherDto(
    @param:Json(name = "elevation")
    val elevation: Double,
    @param:Json(name = "generationtime_ms")
    val generationTimeMs: Double,
    @param:Json(name = "hourly")
    val hourly: Hourly,
    @param:Json(name = "hourly_units")
    val hourlyUnits: HourlyUnits,
    @param:Json(name = "latitude")
    val latitude: Double,
    @param:Json(name = "longitude")
    val longitude: Double,
    @param:Json(name = "timezone")
    val timezone: String,
    @param:Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,
    @param:Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Int
)