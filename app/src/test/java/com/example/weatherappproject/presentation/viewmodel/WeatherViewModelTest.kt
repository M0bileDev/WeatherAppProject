package com.example.weatherappproject.presentation.viewmodel

import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.presentation.mapper.WeatherMapperPresentation
import io.mockk.mockk
import org.junit.Before

class WeatherViewModelTest {

    private lateinit var weatherViewModel: WeatherViewModel

    private val weatherRepository = mockk<DefaultWeatherRepository>()
    private val locationTracker = mockk<DefaultLocationTracker>()
    private val weatherMapperPresentation = mockk<WeatherMapperPresentation>()

    @Before
    fun setup() {
        weatherViewModel = WeatherViewModel(
            weatherRepository,
            locationTracker,
            weatherMapperPresentation
        )
    }

}