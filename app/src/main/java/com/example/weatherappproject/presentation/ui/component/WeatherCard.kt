package com.example.weatherappproject.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappproject.R
import com.example.weatherappproject.presentation.model.WeatherPresentation
import com.example.weatherappproject.presentation.ui.dateFormatterHHmm


@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    currentWeather: WeatherPresentation?
) = currentWeather?.let { presentation ->
        Card(
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val currentTime = remember(presentation.timeData) {
                    presentation.timeData.format(
                        dateFormatterHHmm
                    )
                } ?: stringResource(R.string.no_data)

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(R.string.today_x, currentTime)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(presentation.weatherType.iconRes),
                    contentDescription = stringResource(R.string.weather_illustration)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.temperature_x_celsius,presentation.temperatureCelsiusData), fontSize = 50.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(presentation.weatherType.weatherDesc), fontSize = 20.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherSection(
                        value = presentation.pressureData,
                        unit = stringResource(R.string.hpa),
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_pressure
                        ),
                        contentDescription = stringResource(R.string.pressure)
                    )
                    WeatherSection(
                        value = presentation.windSpeedData,
                        unit = stringResource(R.string.km_h),
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_wind
                        ),
                        contentDescription = stringResource(R.string.wind_speed)
                    )
                    WeatherSection(
                        value = presentation.humidityData,
                        unit = stringResource(R.string.percent_unit),
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_drop
                        ),
                        contentDescription = stringResource(R.string.humidity)
                    )
                }
            }
        }
    }