package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme

@Composable
fun MainNavBar(
    onNewOrderSelected: () -> Unit,
    onOrderHistorySelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier) {
        NavigationBarItem(
            selected = true,
            onClick = onNewOrderSelected,
            icon = {
                Icon(Icons.Default.Add, contentDescription = null)
            },
            label = {
                Text(stringResource(R.string.new_order))
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = onOrderHistorySelected,
            icon = {
                Icon(Icons.Default.List, contentDescription = null)
            },
            label = {
                Text(stringResource(R.string.order_history))
            }
        )
    }
}

@Preview
@Composable
fun NavBarPreview() {
    AppTheme {
        MainNavBar(
            onNewOrderSelected = {},
            onOrderHistorySelected = {}
        )
    }
}
