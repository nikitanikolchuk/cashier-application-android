package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.data.model.OrderDetailResponseDto
import cz.cvut.fit.kot.cashierapplication.ui.state.OrderState
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme

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
private fun OrderInfoDetail(
    orderDetail: OrderDetailResponseDto,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "${orderDetail.name} x ${orderDetail.quantity}",
            modifier = Modifier
                .weight(0.7f)
                .padding(start = 25.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = "${orderDetail.price * orderDetail.quantity} ${stringResource(R.string.currency)}",
            modifier = Modifier.weight(0.3f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
private fun OrderInfoDetailList(
    orderDetails: List<OrderDetailResponseDto>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(orderDetails) { detail -> OrderInfoDetail(detail) }
    }
}

@Composable
fun OrderInfo(
    order: OrderState,
    onChooseOrder: (OrderState?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Order #${order.id}",
            modifier = Modifier.padding(top = 64.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = order.localDateTime,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
        )
        OrderInfoDetailList(
            orderDetails = order.details,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
        )
        Text(
            text = "${stringResource(R.string.total)}: ${order.price} ${stringResource(R.string.currency)}",
            modifier = Modifier.padding(vertical = 24.dp),
            fontSize = 24.sp
        )
        Button(
            onClick = { onChooseOrder(null) },
            modifier = Modifier
                .width(192.dp)
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.back),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderInfoDetailPreview() {
    AppTheme {
        OrderInfoDetail(sampleOrderDetail)
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderInfoDetailListPreview() {
    AppTheme {
        OrderInfoDetailList(sampleOrder.details)
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderInfoPreview() {
    AppTheme {
        OrderInfo(
            order = sampleOrder,
            onChooseOrder = {}
        )
    }
}
