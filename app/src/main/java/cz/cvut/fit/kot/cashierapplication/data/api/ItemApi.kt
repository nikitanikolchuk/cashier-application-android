package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto
import retrofit2.http.GET

/**
 * Interface representing API endpoints for items
 */
interface ItemApi {
    @GET("items")
    suspend fun readAll(): List<ItemResponseDto>
}
