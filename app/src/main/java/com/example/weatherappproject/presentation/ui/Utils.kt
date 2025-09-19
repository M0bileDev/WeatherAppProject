package com.example.weatherappproject.presentation.ui

import java.time.format.DateTimeFormatter

val dateFormatterHHmm: DateTimeFormatter? by lazy {
    DateTimeFormatter.ofPattern("HH:mm")
}