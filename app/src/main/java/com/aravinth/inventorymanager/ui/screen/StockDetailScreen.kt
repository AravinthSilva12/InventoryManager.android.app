package com.aravinth.inventorymanager.ui.screen
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@Composable
fun StockDetailScreen(navController: NavController, itemId: Int) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val viewModel: StockViewModel = viewModel(
        factory = object: ViewModelProvider.Factory{
            override fun <T: ViewModel>
                    create(modelClass: Class<T>): T {
                return StockViewModel(application) as T
            }
        }
    )
    LaunchedEffect(Unit) { viewModel.loadItems() }
    val item = viewModel.items.find { it.id == itemId }
    var itemToDelete by remember { mutableStateOf<StockItem?>(null) }
    if (item == null) {
        Text("Item not found!")
        return
    }

    val isLowStock = item.quantity <= item.reorderLevel

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text("Stock details", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (isLowStock) {
            Text(text = "⚠️ Low Stock", color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text("Item Name: ${item.name}")
        Text("Purchase Price: ₹${item.purchasePrice}")
        Text("Selling Price: ₹${item.sellingPrice}")
        Text("Quantity: ${item.quantity}")
        Text("Reorder Level: ${item.reorderLevel}")
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Button(onClick = { navController.navigate("edit_stock/${item.id}") })
            {
                Text("Edit")
            }

            Button(onClick = {
                itemToDelete = item
            }) {
                Text("Delete")
            }
        }
    }

         //Dialog outside column:
        if (itemToDelete != null) {
            AlertDialog(onDismissRequest = { itemToDelete = null },
                title = { Text("Delete item") },
                text = { Text("Delete\"${itemToDelete?.name}\"?\nThis action cannot be undone.")
                       },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteStockItem(itemToDelete!!.id)
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                        itemToDelete = null
                        navController.popBackStack()
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        itemToDelete = null
                    }) {
                        Text("Cancel") }
                }
            )
        }
    }





