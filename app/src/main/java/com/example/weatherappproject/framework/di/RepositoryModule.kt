package com.example.weatherappproject.framework.di

import com.example.weatherappproject.data.repository.DefaultWeatherRepository
import com.example.weatherappproject.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @[Binds Singleton]
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): DefaultWeatherRepository

}