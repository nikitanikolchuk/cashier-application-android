package cz.cvut.fit.kot.cashierapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cz.cvut.fit.kot.cashierapplication.data.repository.OrderRepository
import cz.cvut.fit.kot.cashierapplication.ui.state.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.ZoneId
import javax.inject.Inject

/**
 * View model for showing information about saved orders
 */
@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    val orders = mutableStateListOf<OrderState>()

    suspend fun refreshOrders() {
        orders.clear()
        orders.addAll(orderRepository.fetchAll().map { orderDto ->
            OrderState(
                orderDto = orderDto,
                timeZoneId = ZoneId.systemDefault()
            )
        })
    }
}
