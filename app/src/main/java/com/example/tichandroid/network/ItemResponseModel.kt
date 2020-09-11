package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single

interface ItemResponseModel {
    fun getItem(
        accessToken: String,
        categoryId: Int,
        cycle: Int,
        latestDate: String,
        startDate: String,
        title: String,
        userId: Int
    ): Single<ItemResponseDto>
}