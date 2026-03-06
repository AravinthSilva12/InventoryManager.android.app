package com.aravinth.inventorymanager.ui.screen
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aravinth.inventorymanager.data.repository.InMemoryStockRepository
import com.aravinth.inventorymanager.domain.usecase.StockUseCase

@Composable
fun StockScreen(){
    val repository = InMemoryStockRepository()
    val useCase = StockUseCase(repository)

    val allItems = useCase.getAllStockItems()
    val lowStockItems = useCase.getLowStockItems()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                text = "Stock Dashboard",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        //Low Stock section :
        item {
            Text(text = "Low stock items", fontWeight = FontWeight.Bold)
        }
            items(lowStockItems) { item ->
                Text("${item.name}   (Qty: ${item.quantity})",
                modifier = Modifier.padding(vertical = 4.dp))
            }

        // All stockItems section :
        item { Spacer(modifier = Modifier.height(16.dp))
               Text(text = "All stock items", fontWeight = FontWeight.Bold) }
            items(allItems) { item ->
                Text("${item.name}  (Qty: ${item.quantity})",
                modifier = Modifier.padding(vertical = 4.dp))
            }
    }
}
