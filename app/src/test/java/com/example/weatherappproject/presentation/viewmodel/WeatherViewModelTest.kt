@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.weatherappproject.presentation.viewmodel

import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.location.LocationData
import com.example.weatherappproject.data.model.weather.WeatherInfoData
import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.framework.dispatcher.DispatcherProvider
import com.example.weatherappproject.presentation.mapper.WeatherMapperPresentation
import com.example.weatherappproject.presentation.model.WeatherInfoPresentation
import com.example.weatherappproject.presentation.model.WeatherViewModelAction
import com.example.weatherappproject.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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
            weatherMapperPresentation,
            object : DispatcherProvider {
                override val io: CoroutineDispatcher = UnconfinedTestDispatcher()
                override val main: CoroutineDispatcher = UnconfinedTestDispatcher()
                override val default: CoroutineDispatcher = UnconfinedTestDispatcher()
            }
        )
    }


    @Test
    fun givenViewModel_whenLocationIsNull_thenWeatherInfoStateIsNull() = runTest {
        coEvery { locationTracker.getCurrentLocation() } returns null

        //given viewModel

        //when current location is null
        weatherViewModel.loadWeatherInfo()

        //then
        assertEquals(null, weatherViewModel.state.value.weatherInfo)
    }

    @Test
    fun givenViewModel_whenLocationIsNull_thenLoadingStateIsFalse() = runTest {
        coEvery { locationTracker.getCurrentLocation() } returns null

        //given viewModel

        //when current location is null
        weatherViewModel.loadWeatherInfo()

        //then
        assertEquals(false, weatherViewModel.state.value.isLoading)
    }

    @Test
    fun givenViewModel_whenLocationIsNull_thenActionIsNoLocationData() = runTest {
        lateinit var action: WeatherViewModelAction
        val job: Job = launch(UnconfinedTestDispatcher()) {
            weatherViewModel.actions.collect {
                action = it
            }
        }
        coEvery { locationTracker.getCurrentLocation() } returns null


        //given viewModel

        //when current location is null
        weatherViewModel.loadWeatherInfo().run { job.cancel() }

        //then
        assertEquals(WeatherViewModelAction.NoLocationData, action)
    }


    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun givenViewModel_whenLocationPermissionHasError_thenActionIsNoLocationData() {
        val mainThreadSurrogate = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)

        //no deadlock between runBlocking and Dispatchers.Main
        runBlocking(mainThreadSurrogate) {
            //given viewModel

            //when current location is null
            weatherViewModel.onLocationPermissionError()

            //then action is NoLocationData
            val action = weatherViewModel.actions.first()
            assertEquals(WeatherViewModelAction.NoLocationData, action)
        }

        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun givenViewModel_whenLocationIsAvailableAndResultIsSuccess_thenWeatherInfoIsPresent() =
        runTest {
            val presentation = WeatherInfoPresentation(
                emptyMap(),
                null
            )
            coEvery { locationTracker.getCurrentLocation() } returns LocationData(0.0, 0.0)
            coEvery { weatherRepository.getWeather(any(), any()) } returns Resource.Success(
                WeatherInfoData(emptyMap(), null)
            )
            every { weatherMapperPresentation.run { any<WeatherInfoData>().toWeatherDataPresentation() } } returns WeatherInfoPresentation(
                emptyMap(),
                null
            )

            //given viewModel

            //when current location is null
            weatherViewModel.loadWeatherInfo()

            //then
            assertEquals(presentation, weatherViewModel.state.value.weatherInfo)
        }

    @Test
    fun givenViewModel_whenLocationIsAvailableAndResultIsError_thenWeatherInfoIsPresent() =
        runTest {

            coEvery { locationTracker.getCurrentLocation() } returns LocationData(0.0, 0.0)
            coEvery {
                weatherRepository.getWeather(
                    any(),
                    any()
                )
            } returns Resource.Error("Error message")

            //given viewModel

            //when current location is null
            weatherViewModel.loadWeatherInfo()

            //then
            assertEquals(null, weatherViewModel.state.value.weatherInfo)
        }

    @Test
    fun givenViewModel_whenLocationIsAvailableAndResultIsError_thenLoadingIsFalse() =
        runTest {

            coEvery { locationTracker.getCurrentLocation() } returns LocationData(0.0, 0.0)
            coEvery {
                weatherRepository.getWeather(
                    any(),
                    any()
                )
            } returns Resource.Error("Error message")

            //given viewModel

            //when current location is null
            weatherViewModel.loadWeatherInfo()

            //then
            assertEquals(false, weatherViewModel.state.value.isLoading)
        }

    @Test
    fun givenViewModel_whenLocationIsAvailableAndResultIsError_thenActionIsApiError() =
        runTest {
            lateinit var action: WeatherViewModelAction
            val job: Job = launch(UnconfinedTestDispatcher()) {
                weatherViewModel.actions.collect {
                    action = it
                }
            }
            coEvery { locationTracker.getCurrentLocation() } returns LocationData(0.0, 0.0)
            coEvery {
                weatherRepository.getWeather(
                    any(),
                    any()
                )
            } returns Resource.Error("Error message")

            //given viewModel

            //when current location is null
            weatherViewModel.loadWeatherInfo().run { job.cancel() }

            //then
            assertEquals(WeatherViewModelAction.ApiError(), action)
        }

    @Test
    fun givenViewModel_whenViewModelIsDestroyed_thenJobIsCancelled() = runTest {

        coEvery { locationTracker.getCurrentLocation() } coAnswers {
            delay(Long.MAX_VALUE)
            null
        }

        //given view model

        //when
        weatherViewModel.loadWeatherInfo()
        weatherViewModel.onCleared()

        //then
        assertEquals(true, weatherViewModel.job?.isCancelled)
    }

    @Test
    fun givenViewModel_whenResultSuccessDataIsNull_thenActionIsApiError() =
        runTest {
            lateinit var action: WeatherViewModelAction
            val job: Job = launch(UnconfinedTestDispatcher()) {
                weatherViewModel.actions.collect {
                    action = it
                }
            }

            coEvery { locationTracker.getCurrentLocation() } returns LocationData(0.0, 0.0)
            coEvery { weatherRepository.getWeather(any(), any()) } returns Resource.Success(
                null
            )
            every { weatherMapperPresentation.run { any<WeatherInfoData>().toWeatherDataPresentation() } } returns WeatherInfoPresentation(
                emptyMap(),
                null
            )

            //given viewModel

            //when current location is null
            weatherViewModel.loadWeatherInfo().run { job.cancel() }

            //then
            assertEquals(WeatherViewModelAction.ApiError(), action)

        }

}