package com.example.weatherappproject.presentation.viewmodel

import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.presentation.mapper.WeatherMapperPresentation
import com.example.weatherappproject.presentation.model.WeatherViewModelAction
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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

    @Test
    fun givenViewModel_whenLocationIsNull_thenWeatherInfoStateIsNull() {
        //given viewModel

        //when current location is null
        coEvery { locationTracker.getCurrentLocation() } returns null
        weatherViewModel.loadWeatherInfo()

        //then
        assertEquals(weatherViewModel.state.value.weatherInfo, null)
    }



}