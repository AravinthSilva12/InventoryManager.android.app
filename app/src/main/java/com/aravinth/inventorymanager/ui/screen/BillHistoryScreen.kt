package com.aravinth.inventorymanager.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aravinth.inventorymanager.data.util.formatDate
import com.aravinth.inventorymanager.domain.model.Bill

@Composable
fun BillHistoryScreen(bills: List<Bill>,
                      onBillClick: (Int) -> Unit) {
    LazyColumn {
        items(bills) { bill ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
                    .clickable { onBillClick(bill.id) })
            {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Bill #${bill.id}")
                    Text("₹${bill.totalAmount}")
                    Text(formatDate(bill.timestamp))
                }
            }
        }
    }
}