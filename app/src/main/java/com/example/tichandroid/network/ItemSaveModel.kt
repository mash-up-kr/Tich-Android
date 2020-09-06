package com.example.tichandroid.network

import com.example.tichandroid.network.model.ItemSaveRequestDto
import io.reactivex.Single

interface ItemSaveModel {
    fun saveItem(
        category: String,
        cycle: Int,
        startDate: String,
        title: String
    ): Single<ItemSaveRequestDto>
}