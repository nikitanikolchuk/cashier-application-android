package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Item model for fetching data from the API
 */
data class ItemResponseDto(
    val id: Int,
    val name: String,
    val price: Int
)
