package cz.cvut.fit.kot.cashierapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cz.cvut.fit.kot.cashierapplication.data.model.ItemRequestDto
import cz.cvut.fit.kot.cashierapplication.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for adding new items
 */
@HiltViewModel
class NewItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {
    val itemName = mutableStateOf("")
    val itemPrice = mutableStateOf(0)

    suspend fun saveItem() {
        if (itemName.value == "" || itemPrice.value == 0)
            return

        itemRepository.save(
            ItemRequestDto(itemName.value, itemPrice.value)
        )
    }
}