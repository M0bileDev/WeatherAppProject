package com.example.weatherappproject.framework.di

import com.example.weatherappproject.BuildConfig
import com.example.weatherappproject.data.remote.api.WeatherApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {

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
    fun provideMoshiConverter(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @[Provides Singleton]
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
}