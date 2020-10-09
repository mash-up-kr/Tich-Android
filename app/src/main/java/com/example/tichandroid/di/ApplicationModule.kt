package com.example.tichandroid.di

import android.content.Context
import com.example.tichandroid.auth.AuthManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideAuthManager(@ApplicationContext context: Context): AuthManager = AuthManager(context)
}