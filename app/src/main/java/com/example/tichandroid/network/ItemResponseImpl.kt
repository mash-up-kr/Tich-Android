package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import com.example.tichandroid.network.service.ItemResponseService
import io.reactivex.Single

class ItemResponseImpl(private val service: ItemResponseService) : ItemResponseModel {
    override fun getItem(
        accessToken: String,
        categoryId: Int,
        cycle: Int,
        latestDate: String,
        startDate: String,
        title: String,
        userId: Int
    ): Single<ItemResponseDto> {
        return service.getItems(
            accessToken,
            categoryId,
            cycle,
            latestDate,
            startDate,
            title,
            userId
        )
    }

}