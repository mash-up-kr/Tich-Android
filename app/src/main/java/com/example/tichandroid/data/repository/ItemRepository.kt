package com.example.tichandroid.data.repository

import com.example.tichandroid.data.model.Item
import com.example.tichandroid.data.remote.ItemRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class ItemRepository @Inject constructor(private val remote: ItemRemoteDataSource) {

    fun fetchItem(itemId: Int): Single<Item> = remote.getItem(itemId)

    fun fetchItems(): Single<List<Item>> = remote.getItems()

    fun saveItem(category: String, cycle: Int, startDate: String, title: String): Single<Item> =
        remote.saveItem(category, title, startDate, cycle)

    fun updateItem(itemId: Int, category: String, title: String, startDate: String, cycle: Int) =
        remote.updateItem(itemId, category, title, startDate, cycle)

    fun deleteItem(itemId: Int): Single<Boolean> = remote.deleteItem(itemId)
}