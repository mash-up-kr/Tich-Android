package com.example.tichandroid.di

import com.example.tichandroid.BASE_URL
import com.example.tichandroid.CONNECT_TIMEOUT
import com.example.tichandroid.READ_TIMEOUT
import com.example.tichandroid.WRITE_TIMEOUT
import com.example.tichandroid.network.service.ItemSaveService
import com.example.tichandroid.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val okHttpClient: OkHttpClient
    get() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

var retrofitPart = module {
    single<ItemSaveService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ItemSaveService::class.java)
    }
}

var adapterPart = module {

}

var modelPart = module {

}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var DiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)