package com.example.tichandroid.network.service

import com.example.tichandroid.network.model.ItemUpdateRequestDto
import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface ItemUpdateService {
    @Headers("Content-Type: application/json")
    @PUT("api/items")
    fun updateItem(
        @Header("accessToken") accessToken: String,
        @Query("category") category: String,
        @Query("cycle") cycle: Int,
        @Query("itemId") itemId: Int,
        @Query("startDate") startDate: String,
        @Query("title") title: String
    ): Single<ItemUpdateRequestDto>

}