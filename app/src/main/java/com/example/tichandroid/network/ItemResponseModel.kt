package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import com.example.tichandroid.network.model.ItemUpdateRequestDto
import io.reactivex.Single
import retrofit2.http.Query

interface ItemResponseModel {
    fun getItem(
        accessToken:String,
        categoryId: Int,
        cycle: Int,
        latestDate: String,
        startDate: String,
        title: String,
        userId: Int
    ): Single<ItemResponseDto>
}