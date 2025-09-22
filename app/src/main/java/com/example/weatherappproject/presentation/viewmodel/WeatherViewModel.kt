package com.example.weatherappproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.framework.dispatcher.DispatcherProvider
import com.example.weatherappproject.presentation.mapper.WeatherMapperPresentation
import com.example.weatherappproject.utils.Resource
import com.example.weatherappproject.presentation.model.WeatherState
import com.example.weatherappproject.presentation.model.WeatherViewModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: DefaultWeatherRepository,
    private val locationTracker: DefaultLocationTracker,
    private val weatherMapperPresentation: WeatherMapperPresentation,
    dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private var job: Job? = null
    private val scope = CoroutineScope(dispatcherProvider.io)

    private val _actions = MutableSharedFlow<WeatherViewModelAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()


    fun loadWeatherInfo() {
        job?.cancel()
        with(scope) {
            if (!isActive) return@with

            job = launch {

                _state.value = _state.value.copy(
                    isLoading = true
                )

                locationTracker.getCurrentLocation()?.let { locationData ->
                    val (latitude, longitude) = locationData
                    val result = weatherRepository.getWeather(latitude, longitude)

                    when (result) {
                        is Resource.Success -> {

                            val data = result.data
                                ?: throw IllegalStateException("Success data cannot be null")
                            val weatherInfo = with(weatherMapperPresentation) {
                                data.toWeatherDataPresentation()
                            }

                            _state.value = _state.value.copy(
                                weatherInfo = weatherInfo,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                weatherInfo = null,
                                isLoading = false,
                            )
                            _actions.emit(WeatherViewModelAction.ApiError())
                        }
                    }
                } ?: run {
                    _state.value = _state.value.copy(
                        weatherInfo = null,
                        isLoading = false
                    )

                    _actions.emit(WeatherViewModelAction.NoLocationData)
                }
            }
        }
    }

    fun onLocationPermissionError() = with(viewModelScope) {
        launch {
            _actions.emit(WeatherViewModelAction.NoLocationData)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}