package com.aravinth.inventorymanager.ui.screen

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aravinth.inventorymanager.viewmodel.StockViewModel

@Composable
fun StockDataScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext as Application
    val viewModel: StockViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel>
                    create(modelClass: Class<T>): T {
                return StockViewModel(context) as T
            }
        }
    )
    LaunchedEffect(Unit) { viewModel.loadItems() }
    val items = viewModel.items
    val totalItems = items.size
    val totalQuantity = items.sumOf { it.quantity }
    val lowStockCount = items.count { it.quantity <= it.reorderLevel }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    { Text("Stock Data", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

      Text("Total products: $totalItems")
      Text("Total Quantity: $totalQuantity")
      Text("Low Stock Items: $lowStockCount")
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {navController.popBackStack() }
        ) {
            Text("Back")
        }
    }
}