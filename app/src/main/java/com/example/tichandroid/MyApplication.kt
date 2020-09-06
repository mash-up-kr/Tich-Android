package com.example.tichandroid

import android.app.Application
import com.example.tichandroid.di.DiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, DiModule)
    }
}