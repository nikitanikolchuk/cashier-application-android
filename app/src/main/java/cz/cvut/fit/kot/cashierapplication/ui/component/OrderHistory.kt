package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.data.model.OrderDetailResponseDto
import cz.cvut.fit.kot.cashierapplication.ui.state.OrderState
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme
import cz.cvut.fit.kot.cashierapplication.ui.viewmodel.OrderHistoryViewModel

private val sampleOrderDetail = OrderDetailResponseDto(
    orderId = 1,
    itemId = 1,
    name = "item",
    price = 100,
    quantity = 1
)
private val sampleOrder = OrderState(
    id = 1,
    localDateTime = "01.01.23 - 12:00",
    employeeId = 1,
    price = 1000,
    details = List(10) { sampleOrderDetail }
)

@Composable
private fun OrderHistoryRow(
    orderState: OrderState,
    onChooseOrder: (OrderState?) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onChooseOrder(orderState) }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberVectorPainter(Icons.Default.ShoppingCart),
            contentDescription = null,
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = "${orderState.price} ${stringResource(R.string.currency)}",
            modifier = Modifier.weight(0.5f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = orderState.localDateTime,
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun OrderHistoryList(
    orders: List<OrderState>,
    onChooseOrder: (OrderState?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(orders) { order -> OrderHistoryRow(order, onChooseOrder) }
    }
}

@Composable
fun OrderHistoryMenu(
    modifier: Modifier = Modifier,
    orderHistoryViewModel: OrderHistoryViewModel = hiltViewModel()
) {
    val orders = remember { orderHistoryViewModel.orders }
    LaunchedEffect(null) { orderHistoryViewModel.refreshOrders() }
    val chosenOrder = remember { orderHistoryViewModel.chosenOrder }

    Surface(modifier) {
        chosenOrder.value?.let {
            OrderInfo(it, orderHistoryViewModel::chooseOrder)
        } ?: run {
            OrderHistoryList(orders, orderHistoryViewModel::chooseOrder)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderHistoryRowPreview() {
    AppTheme {
        OrderHistoryRow(
            orderState = sampleOrder,
            onChooseOrder = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderHistoryListPreview() {
    AppTheme {
        OrderHistoryList(
            orders = List(3) { sampleOrder },
            onChooseOrder = {}
        )
    }
}
