package cz.cvut.fit.kot.cashierapplication.data.repository

import cz.cvut.fit.kot.cashierapplication.data.api.ApiClient
import cz.cvut.fit.kot.cashierapplication.data.api.ItemApi
import cz.cvut.fit.kot.cashierapplication.data.model.ItemRequestDto
import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for items
 */
@Singleton
class ItemRepository @Inject constructor(apiClient: ApiClient) {
    private val api: ItemApi? = apiClient.create(ItemApi::class.java)

    suspend fun save(@Body item: ItemRequestDto) = withContext(Dispatchers.IO) {
        api?.create(item)
    }

    suspend fun fetchAll(): List<ItemResponseDto> = withContext(Dispatchers.IO) {
        api?.readAll() ?: listOf()
    }
}