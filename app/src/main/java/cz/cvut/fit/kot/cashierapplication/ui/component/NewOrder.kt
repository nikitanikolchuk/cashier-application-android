package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.ui.state.ItemState
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme
import cz.cvut.fit.kot.cashierapplication.ui.viewmodel.NewOrderViewModel

private val sampleItems = listOf(
    ItemState(0, "Name", 100),
    ItemState(0, "Name".repeat(10), 100),
    ItemState(0, "Name", 100_000_000),
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
        val buttonModifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
        val iconModifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color.Black)

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
            modifier = Modifier.weight(1f),
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
            modifier = Modifier.weight(0.2f)
        )
        Text(
            text = itemState.name,
            modifier = Modifier.weight(0.4f),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "${itemState.price} ${stringResource(R.string.currency)}",
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium
        )
        ItemCounter(
            count = itemState.count,
            onCountDecrement = itemState.onCountDecrement,
            onCountIncrement = itemState.onCountIncrement,
            modifier = Modifier.weight(0.2f)
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
fun NewOrderMenu(newOrderViewModel: NewOrderViewModel = viewModel()) {
    val items = remember { newOrderViewModel.items }
    LaunchedEffect(null) {
        newOrderViewModel.refreshItems()
    }
    NewOrderItemList(items)
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
        NewOrderItem(sampleItems.first())
    }
}

@Preview(showBackground = true)
@Composable
private fun NewOrderItemListPreview() {
    AppTheme {
        NewOrderItemList(sampleItems)
    }
}