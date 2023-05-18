package cz.cvut.fit.kot.cashierapplication.data.repository

import cz.cvut.fit.kot.cashierapplication.data.api.ApiClient
import cz.cvut.fit.kot.cashierapplication.data.api.ItemApi
import cz.cvut.fit.kot.cashierapplication.data.model.ItemModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for items
 */
@Singleton
class ItemRepository @Inject constructor(apiClient: ApiClient) {
    private val api: ItemApi? = apiClient.create(ItemApi::class.java)

    suspend fun fetchById(id: Int): ItemModel? =
        api?.fetchById(id.toString())

    suspend fun fetchAll(): List<ItemModel> =
        api?.fetchAll() ?: listOf()
}