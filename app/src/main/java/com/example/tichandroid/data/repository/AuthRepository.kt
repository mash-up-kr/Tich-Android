package com.example.tichandroid.data.repository

import com.example.tichandroid.data.model.UserInfo
import com.example.tichandroid.data.remote.AuthRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthRepository @Inject constructor(private val remote: AuthRemoteDataSource) {

    fun signUp(token: String, name: String, email: String): Single<UserInfo> =
        remote.signUp(token, name, email)

    fun signIn(): Single<UserInfo> = remote.signIn()

    fun saveDevice(deviceToken: String): Completable =
        remote.saveDevice(deviceToken).ignoreElement()
}