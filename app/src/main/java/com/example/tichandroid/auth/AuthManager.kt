package com.example.tichandroid.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AuthManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(AUTH_STATE, Context.MODE_PRIVATE)

    fun getToken(): String? = prefs.getString(ME_AUTH_TOKEN, null)

    fun saveToken(token: String) = prefs.edit { putString(ME_AUTH_TOKEN, token) }

    companion object {
        private const val AUTH_STATE = "auth_states"
        private const val ME_AUTH_TOKEN = "auth_token"
    }
}