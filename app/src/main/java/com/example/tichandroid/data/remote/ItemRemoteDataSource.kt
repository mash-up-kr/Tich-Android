package com.example.tichandroid.data.remote

import com.example.tichandroid.data.ItemService
import com.example.tichandroid.data.model.Item
import com.mashup.android.base.extension.requestBodyOf
import io.reactivex.Single
import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(private val service: ItemService) {
    fun getItem(itemId: Int): Single<Item> = service.getItem(itemId)

    fun getItems(): Single<List<Item>> = service.getItems()

    fun saveItem(
        category: String,
        title: String,
        startDate: String,
        cycle: Int
    ): Single<Item> = service.saveItem(requestBodyOf {
        "category" to category
        "title" to title
        "startDate" to startDate
        "cycle" to cycle
    })

    fun updateItem(
        itemId: Int,
        category: String,
        title: String,
        startDate: String,
        cycle: Int
    ): Single<Item> = service.updateItem(requestBodyOf {
        "itemId" to itemId
        "category" to category
        "title" to title
        "startDate" to startDate
        "cycle" to cycle
    })

    fun deleteItem(itemId: Int): Single<Boolean> =
        service.deleteItem(itemId).map { it.success == true }
}