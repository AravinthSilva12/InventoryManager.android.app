package com.aravinth.inventorymanager.data.repository
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository
import com.aravinth.inventorymanager.viewmodel.StockViewModel

class InMemoryStockRepository: StockRepository {
    companion object {   private val items = mutableListOf<StockItem>()
                         private var nextId = 1
    }
    // Add stock item :
    override suspend fun addStockItem(item: StockItem){
        item.id = nextId++
        items.add(item)
    }
    // Get all stock items :
    override suspend fun getAllStockItems(): List<StockItem> {
        return items
    }
    // Get stock item by id :
    override suspend fun getStockItemById(id: Int): StockItem? {
        return items.find { it.id == id }
    }
    // Delete stock item by id :
    override suspend fun deleteStockItem(id:Int){
        items.removeIf { it.id == id }
    }
    // Update stock item :
    override suspend fun updateStockItem(item : StockItem) {
        val index = items.indexOfFirst { it.id == item.id }
        if(index != -1)
            items[index] = item
    }
}