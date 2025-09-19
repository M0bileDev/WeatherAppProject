package com.example.weatherappproject.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherappproject.R
import com.example.weatherappproject.presentation.ui.theme.WeatherAppProjectTheme

@Composable
fun <T> WeatherSection(
    modifier: Modifier = Modifier,
    value: T,
    unit: String,
    contentDescription: String,
    icon: ImageVector
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = icon,
            contentDescription = contentDescription
        )
        Spacer(Modifier.width(8.dp))
        Text(text = stringResource(R.string.value_unit, value.toString(), unit))
    }
}

@Preview
@Composable
private fun WeatherSectionPreview() {
    WeatherAppProjectTheme {
        WeatherSection(
            value = 30,
            unit = stringResource(R.string.km_h),
            contentDescription = stringResource(R.string.wind_speed),
            icon = ImageVector.vectorResource(
                R.drawable.ic_wind
            )
        )
    }
}

@Preview
@Composable
private fun WeatherSectionPreview2() {
    WeatherAppProjectTheme {
        WeatherSection(
            value = 15.6,
            unit = "km/h",
            contentDescription = "wind speed",
            icon = ImageVector.vectorResource(
                R.drawable.ic_wind
            )
        )
    }
}