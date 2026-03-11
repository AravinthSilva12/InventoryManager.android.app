package com.aravinth.inventorymanager.ui.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@Composable
fun StockScreen(navController: NavController) {
    val viewModel: StockViewModel = viewModel()
    val allItems = viewModel.items
    val lowStockItems = viewModel.getLowStockItems()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {navController.navigate(Screen.AddStock.route)})
        { Icon(Icons.Default.Add, contentDescription = "Add Stock") }
    })
      {padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            item {
                Text(text = "Stock Dashboard", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Low stock items :
            item {
                Text(text = "Low Stock Items", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(lowStockItems) { item ->
                Text(
                    "${item.name} (Qty: ${item.quantity})",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // All stock items :
            item {
                Text(text = "All Stock Items", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            items(allItems) { item ->
            Row(modifier = Modifier.fillMaxWidth().clickable{
                navController.navigate("Stock_detail/${item.id}")}.padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    "${item.name}  (Qty: ${item.quantity})",
                    modifier = Modifier.padding(vertical = 4.dp)
                    )
                    IconButton(onClick = {viewModel.deleteStockItem(item.id)}) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}
