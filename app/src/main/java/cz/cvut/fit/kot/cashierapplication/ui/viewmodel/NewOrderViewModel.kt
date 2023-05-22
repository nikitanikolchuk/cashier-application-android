package cz.cvut.fit.kot.cashierapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cz.cvut.fit.kot.cashierapplication.data.model.OrderDetailRequestDto
import cz.cvut.fit.kot.cashierapplication.data.model.OrderRequestDto
import cz.cvut.fit.kot.cashierapplication.data.repository.ItemRepository
import cz.cvut.fit.kot.cashierapplication.data.repository.OrderRepository
import cz.cvut.fit.kot.cashierapplication.ui.state.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//TODO: replace with a real id
private const val PLACEHOLDER_EMPLOYEE_ID = 1

/**
 * View model for saving state of items and sending orders.
 * Sends PLACEHOLDER_EMPLOYEE_ID because the app does not register employees.
 */
@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    val items = mutableStateListOf<ItemState>()
    val orderPrice = mutableStateOf(0)

    private fun resetPrice() {
        orderPrice.value = items.map { it.price * it.count }.fold(0, Int::plus)
    }

    suspend fun refreshItems() {
        items.clear()
        items.addAll(itemRepository.fetchAll().mapIndexed { index, itemModel ->
            ItemState(
                itemModel = itemModel,
                onCountIncrement = {
                    items[index] = items[index].incrementCount()
                    resetPrice()
                },
                onCountDecrement = {
                    items[index] = items[index].decrementCount()
                    resetPrice()
                }
            )
        })
    }

    private fun resetItemCounts() {
        for (i in 0 until items.size)
            items[i] = items[i].copy(count = 0)
    }

    suspend fun saveOrder() {
        //TODO: add alert
        if (orderPrice.value == 0)
            return

        orderRepository.saveOrder(OrderRequestDto(
            employeeId = PLACEHOLDER_EMPLOYEE_ID,
            details = items.filter { it.count > 0 }.map { item ->
                OrderDetailRequestDto(
                    itemId = item.id,
                    quantity = item.count
                )
            }
        ))

        resetItemCounts()
        resetPrice()
    }
}