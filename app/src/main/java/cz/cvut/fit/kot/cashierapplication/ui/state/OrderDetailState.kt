package cz.cvut.fit.kot.cashierapplication.ui.state

import cz.cvut.fit.kot.cashierapplication.data.model.OrderDetailResponseDto

/**
 * Immutable class representing order detail state
 */
data class OrderDetailState(
    val name: String,
    val quantity: Int,
    val totalPrice: Int
) {
    constructor(
        orderDetailDto: OrderDetailResponseDto
    ) : this(
        name = orderDetailDto.name,
        quantity = orderDetailDto.quantity,
        totalPrice = orderDetailDto.price * orderDetailDto.quantity
    )

    constructor(
        item: ItemState
    ) : this(
        name = item.name,
        quantity = item.count,
        totalPrice = item.price * item.count
    )
}
