package com.example.tichandroid.network.model

import com.google.gson.annotations.SerializedName

data class ItemResponseDto(
    @SerializedName("categoryId")
    var categoryId: Int,
    @SerializedName("cycle")
    var cycle: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("latestDate")
    var latestDate: String,
    @SerializedName("scheduledDate")
    var scheduledDate: String,
    @SerializedName("startDate")
    var startDate: String,
    @SerializedName("userId")
    var userId: Int
)