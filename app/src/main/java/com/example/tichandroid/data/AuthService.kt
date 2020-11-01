package com.example.tichandroid.data

import com.example.tichandroid.data.model.UserInfo
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users/sign-up")
    fun signUp(@Body body: RequestBody): Single<UserInfo>

    @POST("users/sign-in")
    fun signIn(): Single<UserInfo>

    @POST("devices")
    fun saveDevice(@Body body: RequestBody): Single<String>
}