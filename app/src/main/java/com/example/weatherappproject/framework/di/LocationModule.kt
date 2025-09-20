package com.example.weatherappproject.framework.di

import android.content.Context
import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.framework.location.DefaultLocationTrackerImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class LocationModuleBinder {

    @[Binds Singleton]
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTrackerImpl): DefaultLocationTracker


}

@[Module InstallIn(SingletonComponent::class)]
object LocationModuleProvider {

    @[Provides Singleton]
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
}