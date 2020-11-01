package com.example.tichandroid.view.ui.showcycle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object ShowCycleModule {
    @Provides
    fun provideContext(activity: AppCompatActivity): Context = activity
}