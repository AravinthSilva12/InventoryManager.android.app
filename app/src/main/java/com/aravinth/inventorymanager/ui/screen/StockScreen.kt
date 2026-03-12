package com.aravinth.inventorymanager.ui.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.StockFilter
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@Composable
fun StockScreen(navController: NavController) {
    val viewModel: StockViewModel = viewModel()
    LaunchedEffect(Unit) { viewModel.loadItems() }
    val allItems = viewModel.items
    var selectedFilter by remember { mutableStateOf(StockFilter.ALL) }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {navController.navigate(Screen.AddStock.route)})
        { Icon(Icons.Default.Add, contentDescription = "Add Stock") }
    })
      {padding ->
        val filteredItems = when(selectedFilter) {
            StockFilter.LOW -> allItems.filter { it.quantity <= it.reorderLevel }
            StockFilter.IN_STOCK -> allItems.filter { it.quantity > 0 }
            StockFilter.OUT_OF_STOCK -> allItems.filter { it.quantity == 0 }
            StockFilter.ALL ->
                allItems
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            item {
                Text(text = "Stock Dashboard", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Filter
           item {
               Row(
                   modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(vertical = 8.dp),
                   horizontalArrangement = Arrangement.SpaceEvenly
               )
               {
                   Button(onClick = { selectedFilter = StockFilter.ALL}) {
                       Text("ALL")
                   }
                   Button(onClick = { selectedFilter = StockFilter.LOW}) {
                       Text("Low")
                   }
                   Button(onClick = { selectedFilter = StockFilter.IN_STOCK}) {
                       Text("In_Stock")
                   }
                   Button(onClick = { selectedFilter = StockFilter.OUT_OF_STOCK}) {
                       Text("Out_of_Stock")
                   }
               }
           }

            // Empty state handler :
            if(filteredItems.isEmpty()) {
                item {
                    Column(modifier = Modifier.fillMaxWidth().padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No Stock items yet")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Tap + to add your first item")
                    }
                }
            }

            // All stock items :
            items(filteredItems) { item ->
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
