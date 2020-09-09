package com.example.tichandroid.network.service

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ItemDeleteService {
    @Headers("Content-Type: application/json")
    @DELETE("api/items/{itemId}")
    fun deleteItem(
        @Header("accessToken") accessToken: String,
        @Path("itemId") itemId: Int
    ): Single<ItemResponseDto>
}