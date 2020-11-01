package com.example.tichandroid.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

object TichSharedPreferenceImpl : TichSharedPreferences {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(applicationContext: Context) {
        sharedPreferences =
            applicationContext.getSharedPreferences("tich_preferences", Context.MODE_PRIVATE)

        sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->

        }
    }

}