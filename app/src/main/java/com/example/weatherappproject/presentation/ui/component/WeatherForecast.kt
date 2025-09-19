package com.example.weatherappproject.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappproject.R
import com.example.weatherappproject.presentation.model.WeatherPresentation

@Composable
fun WeatherForecast(
    modifier: Modifier = Modifier,
    weatherForToday: List<WeatherPresentation>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.today), fontSize = 20.sp)
        Spacer(Modifier.height(16.dp))
        LazyRow {
            items(weatherForToday) { presentation ->
                HourlyWeatherDisplay(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(end = 16.dp),
                    weatherPresentation = presentation
                )
            }
        }
    }
}