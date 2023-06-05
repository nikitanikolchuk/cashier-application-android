package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface representing API endpoints for items
 */
interface ItemApi {
    @GET("items/{id}")
    suspend fun fetchById(@Path("id") id: Int): ItemResponseDto

    @GET("items")
    suspend fun fetchAll(): List<ItemResponseDto>
}
