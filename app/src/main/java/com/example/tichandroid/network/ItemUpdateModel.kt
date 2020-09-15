package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemUpdateRequestDto
import io.reactivex.Single

interface ItemUpdateModel {
    fun updateItem(
        accessToken: String,
        category: String,
        cycle: Int,
        itemId: Int,
        startDate: String,
        title: String
    ): Single<ItemUpdateRequestDto>
}