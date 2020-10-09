package com.example.tichandroid.di

import com.example.tichandroid.data.AuthService
import com.example.tichandroid.data.ItemService
import com.example.tichandroid.network.TichRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ServiceModule {

    @Provides
    fun provideItemService(): ItemService = TichRetrofit.create()

    @Provides
    fun provideAuthService(): AuthService = TichRetrofit.create()
}