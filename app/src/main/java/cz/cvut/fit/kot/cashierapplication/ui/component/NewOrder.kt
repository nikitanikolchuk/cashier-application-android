package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.ui.state.ItemState
import cz.cvut.fit.kot.cashierapplication.ui.state.OrderDetailState
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme
import cz.cvut.fit.kot.cashierapplication.ui.viewmodel.NewOrderViewModel
import kotlinx.coroutines.launch

private val sampleItem = ItemState(
    id = 0,
    name = "Name",
    price = 100
)

@Composable
private fun ItemCounter(
    count: Int,
    onCountDecrement: () -> Unit,
    onCountIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val buttonModifier = Modifier.size(24.dp)
        val iconModifier = Modifier.border(1.dp, Color.Black)

        IconButton(
            onClick = onCountDecrement,
            modifier = buttonModifier
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                modifier = iconModifier
            )
        }
        Text(
            text = count.toString(),
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
        IconButton(
            onClick = onCountIncrement,
            modifier = buttonModifier
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = iconModifier
            )
        }
    }
}

@Composable
private fun NewOrderItem(
    itemState: ItemState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberVectorPainter(Icons.Default.Sell),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = itemState.name,
            modifier = Modifier.weight(0.7f),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${itemState.price} ${stringResource(R.string.currency)}",
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium
        )
        ItemCounter(
            count = itemState.count,
            onCountDecrement = itemState.onCountDecrement,
            onCountIncrement = itemState.onCountIncrement,
        )
    }
}

@Composable
private fun NewOrderItemList(
    items: List<ItemState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(items) { item -> NewOrderItem(item) }
    }
}

@Composable
private fun NewOrderMenu(
    items: List<ItemState>,
    orderPrice: Int,
    onSaveOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewOrderItemList(
            items = items,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${stringResource(R.string.total)}: $orderPrice ${stringResource(R.string.currency)}",
            modifier = Modifier.padding(vertical = 24.dp),
            fontSize = 24.sp
        )
        Button(
            onClick = onSaveOrder,
            modifier = Modifier
                .width(192.dp)
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.save_order),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun NewOrderInfo(
    price: Int,
    details: List<OrderDetailState>,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    OrderInfo(
        price = price,
        details = details,
        modifier = modifier
    ) {
        OrderInfoButton(onCancel, stringResource(R.string.cancel))
        OrderInfoButton(onConfirm, stringResource(R.string.confirm))
    }
}

@Composable
fun NewOrder(
    modifier: Modifier = Modifier,
    newOrderViewModel: NewOrderViewModel = hiltViewModel()
) {
    val items = remember { newOrderViewModel.items }
    LaunchedEffect(null) { newOrderViewModel.refreshItems() }
    val orderPrice = remember { newOrderViewModel.orderPrice }
    val isOrderShown = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier) {
        if (isOrderShown.value) {
            NewOrderInfo(
                price = orderPrice.value,
                details = items.filter { it.count > 0 }.map(::OrderDetailState),
                onCancel = { isOrderShown.value = false },
                onConfirm = {
                    coroutineScope.launch { newOrderViewModel.saveOrder() }
                    isOrderShown.value = false
                }
            )
        } else {
            NewOrderMenu(
                items = items,
                orderPrice = orderPrice.value,
                onSaveOrder = { isOrderShown.value = (orderPrice.value > 0) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemCounterPreview() {
    AppTheme {
        ItemCounter(
            count = 0,
            onCountDecrement = {},
            onCountIncrement = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewOrderItemPreview() {
    AppTheme {
        NewOrderItem(sampleItem)
    }
}

@Preview(showBackground = true)
@Composable
private fun NewOrderItemListPreview() {
    AppTheme {
        NewOrderItemList(List(3) { sampleItem })
    }
}
