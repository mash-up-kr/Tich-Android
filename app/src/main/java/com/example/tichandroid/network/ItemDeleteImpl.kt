package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import com.example.tichandroid.network.service.ItemDeleteService
import io.reactivex.Single

class ItemDeleteImpl(private val service: ItemDeleteService) : ItemDetailModel {
    override fun detailItem(accessToken: String, itemId: Int): Single<ItemResponseDto> {
        return service.deleteItem(accessToken, itemId)
    }
}