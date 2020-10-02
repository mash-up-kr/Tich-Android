package com.example.tichandroid.data

import com.example.tichandroid.data.model.Item
import com.example.tichandroid.network.PredicateResult
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface ItemService {

    @DELETE("items/{itemId}")
    fun deleteItem(@Path("itemId") itemId: Int): Single<PredicateResult>

    @GET("items/{itemId}")
    fun getItem(@Path("itemId") itemId: Int): Single<Item>

    @GET("items")
    fun getItems(): Single<List<Item>>

    @POST("items")
    fun saveItem(@Body body: RequestBody): Single<Item>

    @PUT("items")
    fun updateItem(@Body body: RequestBody): Single<Item>
}
