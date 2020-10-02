package com.example.tichandroid.network

import com.example.tichandroid.auth.AuthManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authManager: AuthManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .apply {
                    authManager.getToken()?.let {
                        header("authorization", it)
                    }
                }
                .build()
        )
    }
}