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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherappproject.R
import com.example.weatherappproject.presentation.model.WeatherPresentation
import com.example.weatherappproject.presentation.ui.dateFormatterHHmm

@Composable
fun HourlyWeatherDisplay(
    modifier: Modifier = Modifier,
    weatherPresentation: WeatherPresentation
) = with(weatherPresentation) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val time = remember(timeData) {
            timeData.format(
                dateFormatterHHmm
            )
        } ?: stringResource(R.string.no_data)
        Text(text = time)
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(weatherType.iconRes),
            contentDescription = stringResource(weatherType.weatherDesc),
        )
        Text(
            text = stringResource(
                R.string.temperature_x_celsius,
                temperatureCelsiusData
            )
        )
    }
}