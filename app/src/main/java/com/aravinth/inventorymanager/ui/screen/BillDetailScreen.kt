package com.aravinth.inventorymanager.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aravinth.inventorymanager.viewmodel.BillViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillDetailScreen(
    billId: Int,
    viewModel: BillViewModel,
    onBack: () -> Unit
) {
    val items by viewModel.getBillItemsByBillId(billId).collectAsState(initial = emptyList())
    val total = items.sumOf { it.quantity * it.sellingPrice }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Bill #$billId") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            if (items.isEmpty()) {
                Text("No items found for this bill")
            } else {
                LazyColumn {
                    items(items) { item ->
                        Text("${item.name}: ${item.quantity} x ₹${item.sellingPrice}")
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text("Total: ₹$total", fontWeight = FontWeight.Bold)
            }
        }
    }
}