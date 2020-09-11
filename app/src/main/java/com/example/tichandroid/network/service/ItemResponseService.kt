package com.example.tichandroid.network.service

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ItemResponseService {
    @Headers("Content-Type: application/json")
    @GET("api/items")
    fun getItems(
        @Header("accessToken") accessToken: String,
        @Query("categoryId") categoryId: Int,
        @Query("cycle") cycle: Int,
        @Query("latestDate") latestDate: String,
        @Query("startDate") startDate: String,
        @Query("title") title: String,
        @Query("userId") userId: Int
    ): Single<ItemResponseDto>
}