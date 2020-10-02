package com.example.tichandroid.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactory {

    val gson: Gson by lazy {
        GsonBuilder()
            .create()
    }
}