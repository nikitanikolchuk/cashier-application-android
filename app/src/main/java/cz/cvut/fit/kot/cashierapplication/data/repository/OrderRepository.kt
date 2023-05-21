package cz.cvut.fit.kot.cashierapplication.data.repository

import cz.cvut.fit.kot.cashierapplication.data.api.ApiClient
import cz.cvut.fit.kot.cashierapplication.data.api.OrderApi
import cz.cvut.fit.kot.cashierapplication.data.model.OrderRequestDto
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for orders
 */
@Singleton
class OrderRepository @Inject constructor(apiClient: ApiClient) {
    private val api: OrderApi? = apiClient.create(OrderApi::class.java)

    suspend fun saveOrder(order: OrderRequestDto): Unit? =
        api?.saveOrder(order)
}
