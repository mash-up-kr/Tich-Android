package com.example.tichandroid.data.model

import com.google.gson.annotations.SerializedName

data class FaqUpdateRequestDto(
    @SerializedName("answer")
    var answer: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("question")
    var question: String
)