package cz.cvut.fit.kot.cashierapplication.ui.state

import cz.cvut.fit.kot.cashierapplication.data.api.OrderApi
import cz.cvut.fit.kot.cashierapplication.data.model.OrderResponseDto
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Immutable class representing order state.
 * Can represent either data from API or an unfinished order.
 */
data class OrderState(
    val id: Int?,
    val localDateTime: String?,
    val employeeId: Int?,
    val price: Int,
    val details: List<OrderDetailState>
) {
    companion object {
        private val dateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yy - HH:mm")
    }

    /**
     * Datetime from DTO is converted to local using device's time zone ID
     */
    constructor(
        orderDto: OrderResponseDto,
        timeZoneId: ZoneId
    ) : this(
        id = orderDto.id,
        localDateTime = LocalDateTime.ofInstant(
            LocalDateTime.parse(
                orderDto.dateTime,
                OrderApi.DATETIME_FORMATTER
            ).toInstant(ZoneOffset.UTC),
            timeZoneId
        ).format(dateTimeFormatter),
        employeeId = orderDto.employeeId,
        price = orderDto.price,
        details = orderDto.details.map(::OrderDetailState)
    )

    constructor(
        details: List<OrderDetailState>
    ) : this(
        id = null,
        localDateTime = null,
        employeeId = null,
        price = details.sumOf(OrderDetailState::totalPrice),
        details = details
    )
}
