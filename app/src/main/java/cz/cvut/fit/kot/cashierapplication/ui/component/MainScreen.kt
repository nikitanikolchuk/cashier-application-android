package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private enum class NavRoutes {
    NEW_ORDER,
    ORDER_HISTORY
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
            modifier = Modifier.weight(0.9f)
        ) {
            composable(NavRoutes.NEW_ORDER.toString()) {
                NewOrderMenu()
            }
            composable(NavRoutes.ORDER_HISTORY.toString()) {
                OrderHistoryMenu()
            }
        }
        MainNavBar(
            onNewOrderSelected = { navController.navigate(NavRoutes.NEW_ORDER.toString()) },
            onOrderHistorySelected = { navController.navigate(NavRoutes.ORDER_HISTORY.toString()) },
            modifier = Modifier.weight(0.1f)
        )
    }
}
