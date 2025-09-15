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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappproject.R
import com.example.weatherappproject.presentation.model.WeatherDataPresentation
import com.example.weatherappproject.presentation.ui.dateFormatterHHmm


@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    currentWeatherData: WeatherDataPresentation?
) {
    currentWeatherData?.let { dataPresentation ->
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

                val currentTime = remember(dataPresentation.timeData) {
                    dataPresentation.timeData.format(
                        dateFormatterHHmm
                    )
                } ?: "No data"

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = "Today $currentTime"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(dataPresentation.weatherType.iconRes),
                    contentDescription = "Weather illustration"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("${dataPresentation.temperatureCelsiusData}°C", fontSize = 50.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(dataPresentation.weatherType.weatherDesc, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherSection(
                        value = dataPresentation.pressureData,
                        unit = "hpa",
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_pressure
                        ),
                        contentDescription = "pressure"
                    )
                    WeatherSection(
                        value = dataPresentation.windSpeedData,
                        unit = "km/h",
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_wind
                        ),
                        contentDescription = "wind speed"
                    )
                    WeatherSection(
                        value = dataPresentation.humidityData,
                        unit = "%",
                        icon = ImageVector.vectorResource(
                            R.drawable.ic_drop
                        ),
                        contentDescription = "humidity"
                    )
                }
            }
        }
    }

}