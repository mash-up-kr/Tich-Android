package com.example.tichandroid.network

import android.content.Context
import android.os.StatFs
import android.util.Log
import com.example.tichandroid.auth.AuthManager
import com.mashup.android.base.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

object OkHttpClientProvider {

    private const val TAG = "OkHttpClientProvider"

    private const val CACHE_DIRECTORY_NAME = "okhttp_client_cache"
    private const val MIN_DISK_CACHE_SIZE: Long = 5L * 1024L * 1024L // 5MB
    private const val MAX_DISK_CACHE_SIZE: Long = 200L * 1024L * 1024L // 200MB

    private lateinit var cache: Cache

    lateinit var okHttpClient: OkHttpClient
        private set

    private object Interceptors {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }

        val headerInterceptor by lazy { HeaderInterceptor() }
    }

    fun init(context: Context) {
        init(context, CACHE_DIRECTORY_NAME, ::calculateDiskCacheSize)
    }

    fun init(context: Context, cacheDirectoryName: String, calculateMaxSize: (File) -> Long) {
        cache = createCache(context, cacheDirectoryName, calculateMaxSize)
        okHttpClient = buildOkHttpClient(context, cache, Interceptors.httpLoggingInterceptor)
    }

    private fun createCache(
        context: Context,
        cacheDirectoryName: String,
        calculateMaxSize: (File) -> Long
    ): Cache {
        val cacheDirectory = createCacheDirectory(context, cacheDirectoryName)
        val cache = Cache(cacheDirectory, calculateMaxSize(cacheDirectory))
        val sizeText = String.format("%.1fM", cache.size().toFloat() / 1000000f)

        // TODO 로그 확인
        Log.d(TAG, "Create cache for network, ${cacheDirectory.absolutePath}, ${sizeText}B")
        return cache
    }

    private fun createCacheDirectory(context: Context, cacheDirectoryName: String): File {
        val cacheDir = File(context.cacheDir, cacheDirectoryName)
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        return cacheDir
    }

    private fun calculateDiskCacheSize(directory: File): Long {
        val statFs = StatFs(directory.absolutePath)
        val available: Long = statFs.blockCountLong * statFs.blockSizeLong
        val size: Long = (available * 0.02).roundToLong()
        return max(MIN_DISK_CACHE_SIZE, min(MAX_DISK_CACHE_SIZE, size))
    }

    private fun buildOkHttpClient(
        context: Context,
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        hasAuthorization: Boolean = true
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptors.headerInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
            if (hasAuthorization) {
                addInterceptor(AuthorizationInterceptor(AuthManager(context)))
            }
        }
        .cache(cache)
        .build()
}