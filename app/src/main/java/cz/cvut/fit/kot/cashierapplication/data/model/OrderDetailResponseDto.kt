package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Order detail Data Transfer Object for API responses
 */
data class OrderDetailResponseDto(
    val orderId: Int,
    val itemId: Int,
    val name: String,
    val price: Int,
    val quantity: Int
)
