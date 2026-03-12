package com.aravinth.inventorymanager.ui.screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@Composable
fun StockDetailScreen(navController: NavController, itemId: Int) {
    val viewModel: StockViewModel = viewModel()
    LaunchedEffect(Unit) { viewModel.loadItems() }
    val item = viewModel.items.find { it.id == itemId }
    if (item == null) {
        Text("Item not found!")
        return
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Stock details", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Item Name: ${item.name}")
        Text("Purchase Price: ${item.purchasePrice}")
        Text("Selling Price: ${item.sellingPrice}")
        Text("Quantity: ${item.quantity}")
        Text("Reorder Level: ${item.reorderLevel}")
        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.navigate("edit_stock/${item.id}")})
            {Text("Edit") }
        }
    }
}




