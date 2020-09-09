package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemResponseDto
import com.example.tichandroid.network.service.ItemDetailService
import io.reactivex.Single

class ItemDetailImpl(private val service: ItemDetailService) : ItemDetailModel {
    override fun detailItem(accessToken: String, itemId: Int): Single<ItemResponseDto> {
        return service.detailItem(accessToken, itemId)
    }
}