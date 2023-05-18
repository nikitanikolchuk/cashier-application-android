package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface representing API endpoints for items
 */
interface ItemApi {
    @GET("items/{id}")
    suspend fun fetchById(@Path("id") id: String): ItemModel

    @GET("items")
    suspend fun fetchAll(): List<ItemModel>
}