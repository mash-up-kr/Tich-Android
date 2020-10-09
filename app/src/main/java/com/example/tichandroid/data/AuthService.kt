package com.example.tichandroid.data

import com.example.tichandroid.data.model.UserInfo
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthService {

    @POST("users/sign-up")
    fun signUp(@Body body: RequestBody): Single<UserInfo>
}