package cz.cvut.fit.kot.cashierapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.cvut.fit.kot.cashierapplication.R
import cz.cvut.fit.kot.cashierapplication.ui.theme.AppTheme
import cz.cvut.fit.kot.cashierapplication.ui.viewmodel.NewItemViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewItemMenu(
    itemName: String,
    itemPrice: String,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onSaveItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add new item",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = itemName,
            onValueChange = onNameChange,
            label = {
                Text(stringResource(R.string.name))
            },
            singleLine = true
        )
        TextField(
            value = itemPrice,
            keyboardOptions = KeyboardOptions.Default
                .copy(keyboardType = KeyboardType.Number),
            onValueChange = onPriceChange,
            label = {
                Text(stringResource(R.string.price))
            }
        )
        Button(
            onClick = onSaveItem,
            modifier = Modifier.width(192.dp)
        ) {
            Text(
                text = stringResource(R.string.save_item),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun NewItem(
    modifier: Modifier = Modifier,
    newItemViewModel: NewItemViewModel = hiltViewModel()
) {
    val itemName = remember { newItemViewModel.itemName }
    val itemPrice = remember { newItemViewModel.itemPrice }
    val coroutineScope = rememberCoroutineScope()

    NewItemMenu(
        itemName = itemName.value,
        itemPrice = itemPrice.value,
        onNameChange = {
            if (it.length < NewItemViewModel.NAME_MAX_LENGTH)
                itemName.value = it
        },
        onPriceChange = {
            if (it == "" || it.matches(NewItemViewModel.PRICE_REGEX))
                itemPrice.value = it
        },
        onSaveItem = {
            coroutineScope.launch {
                newItemViewModel.saveItem()
                itemName.value = ""
                itemPrice.value = ""
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun NewItemMenuPreview() {
    AppTheme {
        NewItemMenu(
            itemName = "",
            itemPrice = "",
            onNameChange = {},
            onPriceChange = {},
            onSaveItem = {}
        )
    }
}