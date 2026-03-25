package com.aravinth.inventorymanager.ui.screen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.viewmodel.BillViewModel
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun BillScreen(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val billViewModel: BillViewModel = viewModel(factory = object: ViewModelProvider.Factory {
        override fun <T: ViewModel>
                create(modelClass: Class<T>): T {
            return BillViewModel(application) as T
        }
    } )

    val stockViewModel: StockViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T: ViewModel>
                create(modelClass: Class<T>): T{
            return StockViewModel(application) as T
        }
    } )
    LaunchedEffect(Unit) { billViewModel.loadItems() }
    val total = billViewModel.total
    val billItems = billViewModel.items
    val stockItems = stockViewModel.items

    var quantity by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf<StockItem?>(null) }

    Scaffold{innerPadding->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)
            .fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start)
        {
            Text("Billing", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(stockItems) {item ->
                    Text(text = item.name, modifier = Modifier.fillMaxWidth().
                    clickable(onClick = {selectedItem = item})
                        .padding(8.dp))
                }
            }
            //Selected item:
            if(selectedItem != null) {
                Text("Selected: ${selectedItem!!.name}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Quantity Input:
            OutlinedTextField(value = quantity, onValueChange = {quantity = it},
                label = {Text("Quantity")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            //Add button:
            Button(modifier = Modifier.fillMaxWidth().padding(4.dp),
                onClick = {
                val selected = selectedItem ?: return@Button
                val qty = quantity.toIntOrNull() ?:return@Button

                val item = BillItem(stockItemId = selected.id, name = selected.name,
                    sellingPrice = selected.sellingPrice, quantity = qty)

                    billViewModel.addItem(item)

                    selectedItem = null
                    quantity = ""
            }) {
                Text("Add item")
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Bill Items List:
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(billItems) {item->
                    Text("${item.name} - ${item.quantity} x ₹${item.sellingPrice}")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            //Total:
            Text("Total: ₹$total", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            //Generate Bill:
            Button(modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = {billViewModel.generateBill()}) {
                Text("Generate Bill")
            }
        }
    }
}
