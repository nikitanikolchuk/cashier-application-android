package cz.cvut.fit.kot.cashierapplication.data.model

/**
 * Order Data Transfer Object for API requests
 */
data class OrderRequestDto(
    val employeeId: Int,
    val details: List<OrderDetailRequestDto>
)
