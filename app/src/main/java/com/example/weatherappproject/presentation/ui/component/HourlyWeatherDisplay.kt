package com.example.weatherappproject.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherappproject.presentation.model.WeatherDataPresentation
import com.example.weatherappproject.presentation.ui.dateFormatterHHmm

@Composable
fun HourlyWeatherDisplay(
    modifier: Modifier = Modifier,
    weatherDataPresentation: WeatherDataPresentation
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val time = remember(weatherDataPresentation.timeData) {
            weatherDataPresentation.timeData.format(
                dateFormatterHHmm
            )
        } ?: "No data"
        Text(text = time)
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(weatherDataPresentation.weatherType.iconRes),
            contentDescription = weatherDataPresentation.weatherType.weatherDesc,
        )
        Text(text = "${weatherDataPresentation.temperatureCelsiusData}°C")
    }

}