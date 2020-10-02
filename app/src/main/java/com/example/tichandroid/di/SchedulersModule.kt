package com.example.tichandroid.di

import com.example.tichandroid.reactivex.scheduler.BaseSchedulerProvider
import com.example.tichandroid.reactivex.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object SchedulersModule {
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider
}