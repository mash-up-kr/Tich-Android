package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemSaveRequestDto
import com.example.tichandroid.network.service.ItemSaveService
import io.reactivex.Single

class ItemSaveImpl(private val service: ItemSaveService) : ItemSaveModel {
    override fun saveItem(
        category: String,
        cycle: Int,
        startDate: String,
        title: String
    ): Single<ItemSaveRequestDto> {
        return service.saveItem(
            category = category,
            cycle = cycle,
            startDate = startDate,
            title = title
        )
    }

}