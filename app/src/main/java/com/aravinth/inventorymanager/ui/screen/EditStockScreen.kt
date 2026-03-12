package com.aravinth.inventorymanager.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.StockViewModel


@Composable
fun EditStockScreen(navController: NavController, itemId:Int){
val viewModel: StockViewModel = viewModel()
val item = viewModel.items.find { it.id == itemId }
    if(item == null){
        Text("Item not found")
        return
    }
var name by remember { mutableStateOf(item.name) }
var purchasePrice by remember { mutableStateOf(item.purchasePrice.toString()) }
var sellingPrice by remember { mutableStateOf(item.sellingPrice.toString()) }
var quantity by remember { mutableStateOf(item.quantity.toString()) }
var reorderLevel by remember { mutableStateOf(item.reorderLevel.toString()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)){
        Text(text = "Update Stock Item", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = {name = it}, label = {Text("Item Name")})
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = purchasePrice, onValueChange = {purchasePrice = it}, label = {Text("Purchasing price")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = sellingPrice, onValueChange = {sellingPrice = it}, label = {Text("Selling Price")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = quantity, onValueChange = {quantity = it}, label = {Text("Quantity of the item")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = reorderLevel, onValueChange = {reorderLevel = it}, label = {Text("Re-Order level")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            if(name.isBlank() ||
                purchasePrice.isBlank() ||
                sellingPrice.isBlank() ||
                quantity.isBlank() ||
                reorderLevel.isBlank()){
                return@Button
            }
            val purchase = purchasePrice.toDoubleOrNull() ?: return@Button
            val selling = sellingPrice.toDoubleOrNull() ?: return@Button
            val quant = quantity.toIntOrNull() ?: return@Button
            val reOrder = reorderLevel.toIntOrNull() ?: return@Button

                val updatedItem = item.copy(
                name = name,
                purchasePrice = purchase,
                sellingPrice = selling,
                quantity = quant,
                reorderLevel = reOrder,
                supplierId = item.supplierId)
            viewModel.updateStockItem(updatedItem)
            navController.popBackStack()
        }){
            Text("Update")
        }
    }
}