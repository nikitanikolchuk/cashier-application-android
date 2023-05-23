package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Order Data Transfer Object for API responses
 */
data class OrderResponseDto(
    val id: Int,
    val dateTime: String,
    val employeeId: Int,
    val price: Int,
    val details: List<OrderDetailResponseDto>
)
