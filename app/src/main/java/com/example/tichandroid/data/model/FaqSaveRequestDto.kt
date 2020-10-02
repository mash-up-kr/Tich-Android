package com.example.tichandroid.data.model

import com.google.gson.annotations.SerializedName

data class FaqSaveRequestDto(
    @SerializedName("answer")
    var answer: String,
    @SerializedName("question")
    var question: String
)