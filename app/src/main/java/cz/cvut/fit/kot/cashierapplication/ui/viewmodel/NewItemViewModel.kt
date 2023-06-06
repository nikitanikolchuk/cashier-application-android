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
    val itemPrice = mutableStateOf("")

    companion object {
        const val NAME_MAX_LENGTH = 50
        val PRICE_REGEX = "([1-9][0-9]{0,4})".toRegex()
    }

    suspend fun saveItem() {
        if (itemName.value.length !in (1..NAME_MAX_LENGTH) ||
            !itemPrice.value.matches(PRICE_REGEX)
        ) return

        itemRepository.save(
            ItemRequestDto(itemName.value, itemPrice.value.toInt())
        )
    }
}