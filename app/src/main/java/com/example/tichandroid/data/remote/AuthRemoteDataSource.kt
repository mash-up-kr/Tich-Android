package com.example.tichandroid.data.remote

import com.example.tichandroid.data.AuthService
import com.example.tichandroid.data.model.UserInfo
import com.mashup.android.base.extension.requestBodyOf
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val service: AuthService) {
    fun signUp(
        token: String,
        name: String,
        email: String
    ): Single<UserInfo> = service.signUp(requestBodyOf {
        "token" to token
        "name" to name
        "email" to email
    })

    fun signIn(): Single<UserInfo> = service.signIn()

    fun saveDevice(deviceToken: String): Single<String> = service.saveDevice(requestBodyOf {
        "token" to deviceToken
    })
}