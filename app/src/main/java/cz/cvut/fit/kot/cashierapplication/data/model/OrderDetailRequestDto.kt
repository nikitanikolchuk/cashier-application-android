package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Order detail Data Transfer Object for API requests
 */
data class OrderDetailRequestDto(
    val itemId: Int,
    val quantity: Int
)
