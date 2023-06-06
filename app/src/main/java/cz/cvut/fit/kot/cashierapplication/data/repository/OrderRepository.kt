package cz.cvut.fit.kot.cashierapplication.data.repository

import cz.cvut.fit.kot.cashierapplication.data.api.ApiClient
import cz.cvut.fit.kot.cashierapplication.data.api.OrderApi
import cz.cvut.fit.kot.cashierapplication.data.model.OrderRequestDto
import cz.cvut.fit.kot.cashierapplication.data.model.OrderResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for orders
 */
@Singleton
class OrderRepository @Inject constructor(apiClient: ApiClient) {
    private val api: OrderApi? = apiClient.create(OrderApi::class.java)

    suspend fun save(order: OrderRequestDto): Unit? = withContext(Dispatchers.IO) {
        api?.create(order)
    }

    suspend fun fetchAll(): List<OrderResponseDto> = withContext(Dispatchers.IO) {
        api?.readAll() ?: listOf()
    }
}
