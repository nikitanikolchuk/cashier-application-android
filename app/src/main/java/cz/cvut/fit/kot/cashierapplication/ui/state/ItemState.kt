package cz.cvut.fit.kot.cashierapplication.ui.state

import cz.cvut.fit.kot.cashierapplication.data.model.ItemResponseDto

private const val MAX_COUNT = 99

/**
 * Immutable class representing item state with data from API and current count
 */
data class ItemState(
    val id: Int,
    val name: String,
    val price: Int,
    val count: Int = 0,
    val onCountIncrement: () -> Unit = {},
    val onCountDecrement: () -> Unit = {},
    val categories: List<Int> = listOf()
) {
    constructor(
        itemDto: ItemResponseDto,
        count: Int = 0,
        onCountIncrement: () -> Unit = {},
        onCountDecrement: () -> Unit = {},
        categories: List<Int> = listOf()
    ) : this(
        id = itemDto.id,
        name = itemDto.name,
        price = itemDto.price,
        count = count,
        onCountIncrement = onCountIncrement,
        onCountDecrement = onCountDecrement,
        categories = categories
    )

    fun incrementCount() = if (count < MAX_COUNT) copy(count = count + 1) else this

    fun decrementCount() = if (count > 0) copy(count = count - 1) else this
}