package com.example.weatherappproject.domain.model.weather

abstract class WeatherInfo(
    // key - index of weekly representation of day 0-today, 1-tomorrow etc
    // value - weather data that contains info about temperature, pressure etc.
    open val weatherDomainPerDay: Map<Int, List<WeatherDomain>>,
    // representation of time-specific weather -> request data at 1pm, this value will
    // represent weather at 1pm
    open val currentWeather: WeatherDomain?,
    )
