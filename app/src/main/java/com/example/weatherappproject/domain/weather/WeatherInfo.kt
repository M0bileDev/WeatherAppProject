package com.example.weatherappproject.domain.weather

data class WeatherInfo(
    // key - index of weekly representation of day 0-tody, 1-tomorrow etc
    // value - weather data that contains info about temperature, pressure etc.
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    // representation of time-specific weather -> request data at 1pm, this value will
    // represent weather at 1pm
    val currentWeatherData: WeatherData?,

    )
