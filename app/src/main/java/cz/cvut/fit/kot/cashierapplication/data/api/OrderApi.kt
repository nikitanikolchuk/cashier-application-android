package cz.cvut.fit.kot.cashierapplication.data.api

import cz.cvut.fit.kot.cashierapplication.data.model.OrderRequestDto
import cz.cvut.fit.kot.cashierapplication.data.model.OrderResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.time.format.DateTimeFormatter

/**
 * Interface representing API endpoints for orders.
 * Datetime values represent UTC+0 time zone.
 */
interface OrderApi {
    companion object {
        val DATETIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    }

    @POST("orders")
    suspend fun create(@Body order: OrderRequestDto)

    @GET("orders")
    suspend fun readAll(): List<OrderResponseDto>
}
