package com.example.weatherappproject.framework.di

import com.example.weatherappproject.framework.dispatcher.DispatcherProvider
import com.example.weatherappproject.framework.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class DispatcherModule {

    @[Binds Singleton]
    abstract fun bindDispatchers(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider

}