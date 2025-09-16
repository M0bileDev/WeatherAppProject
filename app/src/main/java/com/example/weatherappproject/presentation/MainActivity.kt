package com.example.weatherappproject.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.weatherappproject.presentation.model.WeatherViewModelAction
import com.example.weatherappproject.presentation.ui.component.WeatherCard
import com.example.weatherappproject.presentation.ui.component.WeatherForecast
import com.example.weatherappproject.presentation.ui.theme.WeatherAppProjectTheme
import com.example.weatherappproject.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }.also { launcher ->
            launcher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }

        enableEdgeToEdge()
        setContent {
            WeatherAppProjectTheme {

                val state by viewModel.state.collectAsStateWithLifecycle()
                val lifecycle = LocalLifecycleOwner.current
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(Unit) {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.actions.collectLatest { action ->
                            when (action) {
                                is WeatherViewModelAction.ApiError -> {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    snackbarHostState.showSnackbar(
                                        action.message ?: "An request error occurred",
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Indefinite
                                    )
                                }

                                is WeatherViewModelAction.NoLocationData -> {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    snackbarHostState.showSnackbar(
                                        "A location error occurred. Please check permissions.",
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Indefinite
                                    )
                                }
                            }
                        }
                    }
                }


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.padding(innerPadding)
                        ) {

                            if (state.weatherInfo == null && !state.isLoading) {
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Column {
                                        Text("There's no data to display. Try again later.")
                                        Button(onClick = {
                                            permissionLauncher.launch(
                                                arrayOf(
                                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                                )
                                            )
                                        }) {
                                            Text("Refresh")
                                        }
                                    }
                                }
                            } else {
                                state.weatherInfo?.let { presentation ->
                                    WeatherCard(
                                        currentWeatherData = presentation.currentWeatherData
                                    )
                                    Spacer(Modifier.height(16.dp))
                                    WeatherForecast(
                                        weatherDataForToday = presentation.weatherDataPerDayData.getOrElse(
                                            0,
                                            defaultValue = { throw IllegalStateException() })
                                    )
                                }
                            }


                        }
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}