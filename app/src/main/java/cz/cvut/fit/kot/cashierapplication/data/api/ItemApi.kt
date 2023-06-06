package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.ItemRequestDto
import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Interface representing API endpoints for items
 */
interface ItemApi {
    @POST("items")
    suspend fun create(@Body item: ItemRequestDto)

    @GET("items")
    suspend fun readAll(): List<ItemResponseDto>
}
