package cz.cvut.fit.kot.cashierapplication.data.repository

import cz.cvut.fit.kot.cashierapplication.data.api.ApiClient
import cz.cvut.fit.kot.cashierapplication.data.api.ItemApi
import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for items
 */
@Singleton
class ItemRepository @Inject constructor(apiClient: ApiClient) {
    private val api: ItemApi? = apiClient.create(ItemApi::class.java)

    suspend fun fetchAll(): List<ItemResponseDto> = withContext(Dispatchers.IO) {
        api?.readAll() ?: listOf()
    }
}