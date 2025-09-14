package com.example.weatherappproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.location.LocationData
import com.example.weatherappproject.data.model.weather.WeatherInfo
import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.domain.model.utils.Resource
import com.example.weatherappproject.presentation.mapper.toWeatherDataPresentation
import com.example.weatherappproject.presentation.model.WeatherState
import com.example.weatherappproject.presentation.model.WeatherViewModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val locationTracker: DefaultLocationTracker
) : ViewModel() {


    private val _actions = MutableSharedFlow<WeatherViewModelAction>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    fun loadWeatherInfo() = with(viewModelScope) {
        if (!isActive) return@with

        launch {
            _state.value = _state.value.copy(
                isLoading = true
            )

            locationTracker.getCurrentLocation()?.let { locationData ->
                val (latitude, longitude) = locationData as LocationData
                val result = weatherRepository.getWeather(latitude, longitude)
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            weatherInfo = (result.data as WeatherInfo).toWeatherDataPresentation(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                        )
                        _actions.emit(WeatherViewModelAction.ApiError)
                    }
                }
            } ?: run {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                _actions.emit(WeatherViewModelAction.NoLocationData)
            }
        }
    }

}