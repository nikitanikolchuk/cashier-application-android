package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.OrderRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface representing API endpoints for orders
 */
interface OrderApi {
    @POST("orders")
    suspend fun saveOrder(@Body order: OrderRequestDto)
}
