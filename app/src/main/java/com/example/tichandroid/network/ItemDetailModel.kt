package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single

interface ItemDetailModel {
    fun detailItem(
        accessToken: String,
        itemId: Int
    ): Single<ItemResponseDto>
}