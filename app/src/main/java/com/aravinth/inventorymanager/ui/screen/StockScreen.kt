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
import com.aravinth.inventorymanager.domain.model.StockItem

@Composable
fun StockScreen(){
    val repository = InMemoryStockRepository()
    // Temporary test data
    repository.addStockItem(
        StockItem(1, "Mouse", 200.0, 350.0, 2, 5, null)
    )
    repository.addStockItem(
        StockItem(2, "Keyboard", 400.0, 600.0, 10, 3, null)
    )
    repository.addStockItem(
        StockItem(3, "Monitor", 5000.0, 6500.0, 1, 2, null)
    )
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
