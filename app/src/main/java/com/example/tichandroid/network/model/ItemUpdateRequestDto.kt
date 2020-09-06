package com.example.tichandroid.network.model

import com.google.gson.annotations.SerializedName

data class ItemUpdateRequestDto(
    @SerializedName("category")
    var category:String,
    @SerializedName("cycle")
    var cycle:Int,
    @SerializedName("itemId")
    var itemId:Int,
    @SerializedName("startDate")
    var startDate:String,
    @SerializedName("title")
    var title:String
)