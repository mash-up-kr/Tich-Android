package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single

interface ItemDeleteModel {
    fun deleteItem(
        accessToken: String,
        itemId: Int
    ): Single<ItemResponseDto>
}