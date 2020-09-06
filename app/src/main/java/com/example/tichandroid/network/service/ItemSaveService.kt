package com.example.tichandroid.network.service

import com.example.tichandroid.network.model.ItemSaveRequestDto
import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ItemSaveService {
    @Headers("Content-Type: application/json")
    @POST("/api/items")
    fun saveItem(
        @Query("category") category: String,
        @Query("cycle") cycle: Int,
        @Query("startDate") startDate: String,
        @Query("title") title: String
    ): Single<ItemSaveRequestDto>
}