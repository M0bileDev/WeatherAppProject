package com.example.weatherappproject.framework.di

import com.example.weatherappproject.data.mappers.WeatherMapper
import com.example.weatherappproject.data.mappers.WeatherMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class MapperModule {

    @[Binds Singleton]
    abstract fun bindWeatherMapper(weatherMapperImpl: WeatherMapperImpl): WeatherMapper
}