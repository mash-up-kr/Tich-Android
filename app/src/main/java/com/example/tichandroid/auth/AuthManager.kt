package com.example.tichandroid.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AuthManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(AUTH_STATE, Context.MODE_PRIVATE)

    fun getToken(): String? = prefs.getString(ME_AUTH_TOKEN, null)

    fun saveToken(token: String) = prefs.edit { putString(ME_AUTH_TOKEN, token) }

    fun saveUserName(userName: String) = prefs.edit { putString(AUTH_USERNAME, userName) }

    fun getUserName(): String? = prefs.getString(AUTH_USERNAME, null)

    companion object {
        private const val AUTH_STATE = "auth_states"
        private const val ME_AUTH_TOKEN = "auth_token"
        private const val AUTH_USERNAME = "auth_user_name"
    }
}