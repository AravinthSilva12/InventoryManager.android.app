package com.aravinth.inventorymanager.ui.screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.BillViewModel
import com.aravinth.inventorymanager.viewmodel.StockViewModel
import com.aravinth.inventorymanager.viewmodel.applicationViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillScreen(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val billViewModel: BillViewModel = viewModel(
        factory = applicationViewModelFactory(application) { BillViewModel(it) }
    )

    val stockViewModel: StockViewModel = viewModel(
        factory = applicationViewModelFactory(application) { StockViewModel(it) }
    )

    LaunchedEffect(Unit) {
        billViewModel.loadItems()
        stockViewModel.loadItems()
    }

    val billViewModel: BillViewModel = viewModel(factory = applicationViewModelFactory(application) {
        BillViewModel(it) }
    )

    val stockViewModel: StockViewModel = viewModel(factory = applicationViewModelFactory(application) {
        StockViewModel(it) }
    )

    LaunchedEffect(Unit) { billViewModel.loadItems()
                                  stockViewModel.loadItems()}

    val total = billViewModel.total
    val billItems = billViewModel.items
    val errorMessage = billViewModel.errorMessage

    var searchQuery by remember { mutableStateOf("") }
    val filteredItems = stockViewModel.items.filter { it.name.contains(searchQuery, ignoreCase = true) }

    var quantity by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf<StockItem?>(null) }

    val parsedQty = quantity.toIntOrNull()
    val isValid = selectedItem != null && parsedQty != null && parsedQty > 0 && parsedQty <= (selectedItem?.quantity ?: 0)

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Billing", fontSize = 22.sp, fontWeight = FontWeight.Bold)

                IconButton(onClick = { navController.navigate(Screen.BillHistory.route) }) {
                    Icon(imageVector = Icons.Default.History, contentDescription = "Bill History")
    Scaffold { innerPadding->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)
            .fillMaxSize().verticalScroll(rememberScrollState())
        )
        {   //Bill history button at top:
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
             {
               Text("Billing", fontSize = 22.sp, fontWeight = FontWeight.Bold)

               IconButton(onClick = { navController.navigate(Screen.BillHistory.route) })
                        {Icon(imageVector = Icons.Default.History, contentDescription = "Bill History") }
             }

            //Search Field:
            OutlinedTextField(value = searchQuery, onValueChange = {searchQuery = it}, label = {Text("Search item")},
                modifier = Modifier.fillMaxWidth())

            if(searchQuery.isNotEmpty() && filteredItems.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 150.dp)) {
                items(filteredItems) {item ->
                    Text(text = item.name, modifier = Modifier.fillMaxWidth().background(
                        if(selectedItem == item) Color(0xFFE0E0E0)
                        else Color.Transparent
                    )
                        .clickable{selectedItem = item
                              searchQuery = ""}
                        .padding(8.dp)
                    )
                }
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search item") },
                modifier = Modifier.fillMaxWidth()
            )

            if (searchQuery.isNotEmpty() && filteredItems.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 150.dp)) {
                    items(filteredItems) { item ->
                        Text(
                            text = item.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (selectedItem == item) Color(0xFFE0E0E0) else Color.Transparent)
                                .clickable {
                                    selectedItem = item
                                    searchQuery = ""
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            selectedItem?.let { Text("Selected: ${it.name}") }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            //Empty selection logic:
            val parsedQty = quantity.toIntOrNull()
            val isValid = selectedItem != null &&
                    parsedQty != null &&
                    parsedQty > 0 &&
                    parsedQty <= (selectedItem?.quantity ?: 0)

            Button(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                onClick = {
                    val selected = selectedItem ?: return@Button
                    val qty = quantity.toIntOrNull() ?: return@Button
                    val item = BillItem(
                        stockItemId = selected.id,
                        name = selected.name,
                        sellingPrice = selected.sellingPrice,
                        quantity = qty
                    )

                    billViewModel.addItem(item)
                    selectedItem = null
                    quantity = ""
                    billViewModel.clearError()
                },
                enabled = isValid
            ) {
                Text("Add item")
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp, max = 250.dp)) {
                items(billItems) { item ->
                    Text("${item.name}: ${item.quantity} x ₹${item.sellingPrice}")
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text("Total: ₹$total", fontWeight = FontWeight.Bold)

            if (!errorMessage.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = errorMessage, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = { billViewModel.generateBill() },
                enabled = billItems.isNotEmpty()
            ) {
            //Generate Bill:
            Button(modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = {billViewModel.generateBill()},
                enabled = billItems.isNotEmpty()) {
                Text("Generate Bill")
            }
        }
    }
}
