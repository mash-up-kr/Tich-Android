package com.example.tichandroid

import android.app.Application
import com.example.tichandroid.network.OkHttpClientProvider
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initOkHttpClientProvider()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initOkHttpClientProvider() {
        OkHttpClientProvider.init(this)
    }
}