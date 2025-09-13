package com.example.weatherappproject.framework.di

import android.content.Context
import com.example.weatherappproject.BuildConfig
import com.example.weatherappproject.data.remote.api.WeatherApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Provides Singleton]
    fun provideWeatherApi(
        moshiConverterFactory: MoshiConverterFactory
    ): WeatherApi =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create()


    @[Provides Singleton]
    fun provideMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    @[Provides Singleton]
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)


}