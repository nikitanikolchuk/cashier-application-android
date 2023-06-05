package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme

private enum class NavRoutes {
    NEW_ORDER,
    ORDER_HISTORY
}

@Composable
private fun MainNavBar(
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

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.NEW_ORDER.toString(),
            modifier = Modifier.weight(1f)
        ) {
            composable(NavRoutes.NEW_ORDER.toString()) { NewOrderMenu() }
            composable(NavRoutes.ORDER_HISTORY.toString()) { OrderHistoryMenu() }
        }
        MainNavBar(
            onNewOrderSelected = {
                navController.navigate(NavRoutes.NEW_ORDER.toString())
            },
            onOrderHistorySelected = {
                navController.navigate(NavRoutes.ORDER_HISTORY.toString())
            }
        )
    }
}

@Preview
@Composable
fun MainNavBarPreview() {
    AppTheme {
        MainNavBar(
            onNewOrderSelected = {},
            onOrderHistorySelected = {}
        )
    }
}
