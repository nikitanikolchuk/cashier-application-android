package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Item model for API requests
 */
data class ItemRequestDto(
    val name: String,
    val price: Int
)
