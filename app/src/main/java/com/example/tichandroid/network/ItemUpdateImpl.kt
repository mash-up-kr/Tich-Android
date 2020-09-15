package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemUpdateRequestDto
import com.example.tichandroid.network.service.ItemUpdateService
import io.reactivex.Single

class ItemUpdateImpl(private val service: ItemUpdateService) : ItemUpdateModel {
    override fun updateItem(
        accessToken: String,
        category: String,
        cycle: Int,
        itemId: Int,
        startDate: String,
        title: String
    ): Single<ItemUpdateRequestDto> {
        return service.updateItem(accessToken, category, cycle, itemId, startDate, title)
    }


}