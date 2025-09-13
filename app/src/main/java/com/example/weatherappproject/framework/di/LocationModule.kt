package com.example.weatherappproject.framework.di

import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.framework.location.DefaultLocationTrackerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class LocationModule {

    @[Binds Singleton]
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTrackerImpl): DefaultLocationTracker

}