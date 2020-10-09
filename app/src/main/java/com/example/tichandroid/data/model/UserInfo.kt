package com.example.tichandroid.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("name")
    val name: String
)