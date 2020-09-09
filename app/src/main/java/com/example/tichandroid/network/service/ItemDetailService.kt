package com.example.tichandroid.network.service

import com.example.tichandroid.network.model.ItemResponseDto
import io.reactivex.Single
import retrofit2.http.*

interface ItemDetailService {
    @Headers("Content-Type: application/json")
    @GET("api/items/{itemId}")
    fun detailItem(
        @Header("accessToken") accessToken:String,
        @Path("itemId") itemId: Int
    ): Single<ItemResponseDto>
}