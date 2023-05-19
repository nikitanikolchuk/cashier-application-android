package cz.cvut.fit.kot.cashierapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cz.cvut.fit.kot.cashierapplication.data.repository.ItemRepository
import cz.cvut.fit.kot.cashierapplication.ui.state.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for saving state of items and sending orders
 */
@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {
    val items = mutableStateListOf<ItemState>()

    suspend fun refreshItems() {
        items.clear()
        items.addAll(itemRepository.fetchAll().mapIndexed { index, itemModel ->
            ItemState(
                itemModel = itemModel,
                onCountIncrement = { items[index] = items[index].incrementCount() },
                onCountDecrement = { items[index] = items[index].decrementCount() }
            )
        })
    }

    fun saveOrder() {
        TODO()
    }
}