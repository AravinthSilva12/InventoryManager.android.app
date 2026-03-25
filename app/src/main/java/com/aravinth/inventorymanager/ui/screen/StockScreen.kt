package com.aravinth.inventorymanager.ui.screen
import android.app.Application
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.StockFilter
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.ui.navigation.Screen
import com.aravinth.inventorymanager.viewmodel.StockViewModel


@Composable
fun StockScreen(navController: NavController) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel: StockViewModel = viewModel(factory = object: ViewModelProvider.Factory{
        override fun <T: ViewModel>
                create(modelClass: Class<T>): T {
            return StockViewModel(application) as T
        }
    }
    )
    LaunchedEffect(Unit) { viewModel.loadItems() }
    var itemToDelete by remember { mutableStateOf<StockItem?>(null) }
    val allItems = viewModel.items
    var selectedFilter by remember { mutableStateOf(StockFilter.ALL) }
    Scaffold(floatingActionButton = {
             Row(horizontalArrangement = Arrangement.spacedBy(12.dp))
             {
          //Stock data button:
                 ExtendedFloatingActionButton(
                     onClick = { navController.navigate("stock_data") },
                     icon = {
                       Icon(Icons.Default.List, contentDescription = "Stock Data")
                     },
                     text = {
                       Text("Stock Data")
                     }
                 )
         // Add button:
                 FloatingActionButton(onClick = { navController.navigate(Screen.AddStock.route) })
                 { Icon(Icons.Default.Add, contentDescription = "Add Stock") }
             }
           })
    { innerPadding ->
        val filteredItems = when (selectedFilter) {
            StockFilter.LOW -> allItems.filter { it.quantity <= it.reorderLevel }
            StockFilter.IN_STOCK -> allItems.filter { it.quantity > 0 }
            StockFilter.OUT_OF_STOCK -> allItems.filter { it.quantity == 0 }
            StockFilter.ALL -> allItems
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .padding(horizontal = 16.dp).padding(bottom = 80.dp)
        )
        {
            item {
                Text(
                    text = "Stock Dashboard",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Filter
            item {
                Text(text = "Filter by", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
            }

            // Filter chips :
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    FilterChip(
                        selected = selectedFilter == StockFilter.ALL,
                        onClick = { selectedFilter = StockFilter.ALL },
                        label = { Text("All") }
                    )

                    FilterChip(
                        selected = selectedFilter == StockFilter.LOW,
                        onClick = { selectedFilter = StockFilter.LOW },
                        label = { Text("Low") })

                    FilterChip(
                        selected = selectedFilter == StockFilter.IN_STOCK,
                        onClick = { selectedFilter = StockFilter.IN_STOCK },
                        label = { Text("In Stock") }
                    )

                    FilterChip(
                        selected = selectedFilter == StockFilter.OUT_OF_STOCK,
                        onClick = { selectedFilter = StockFilter.OUT_OF_STOCK },
                        label = { Text("Out of Stock", maxLines = 1) }
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Empty state handler :
            if (filteredItems.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when(selectedFilter) {
                            StockFilter.LOW -> {
                                Text("✅No low stock items!")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Your inventory is healthy!")
                            }

                            StockFilter.IN_STOCK -> {
                                Text("No items in stock")
                            }

                            StockFilter.OUT_OF_STOCK -> {
                                Text("No out-of-stock items")
                            }

                            StockFilter.ALL -> {
                                Text("📦No Stock items yet")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Tap + to add your first item")
                            }
                        }
                    }
                }
            }

            // All stock items :
            items(filteredItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable {
                        navController.navigate("Stock_detail/${item.id}")
                    }, elevation =
                        CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            val isLowStock = item.quantity <= item.reorderLevel
                            Text(
                                text = "Qty: ${item.quantity}",
                                fontSize = 12.sp,
                                color = if(isLowStock) Color.Red else Color.Gray
                            )
                        }

                        IconButton(onClick = { itemToDelete = item }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }

    // Item deletion dialog box :
    if (itemToDelete != null) {
        AlertDialog(onDismissRequest = { itemToDelete = null },
        title = { Text("Delete item") },
        text = { Text("Delete\"${itemToDelete?.name}\"?\nThis action cannot be undone.") },
        confirmButton = {
            TextButton(onClick = {
                viewModel.deleteStockItem(itemToDelete!!.id)
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                itemToDelete = null
            }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                itemToDelete = null
            }) {
                Text("Cancel")
            }
        } )
    }
}


