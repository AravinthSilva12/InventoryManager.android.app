package com.aravinth.inventorymanager.data.repository
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository

class InMemoryStockRepository: StockRepository {
    private val items = mutableListOf<StockItem>()
    // Add stock item :
    override fun addStockItem(item: StockItem){
        items.add(item)
    }

    // Get all stock items :
    override fun getAllStockItems(): List<StockItem> {
        return items
    }

    // Get stock item by id :
    override fun getStockItemById(id: Int): StockItem? {
        return items.find { it.id == id }
    }
    // Delete stock item by id :
    override fun deleteStockItem(id:Int){
        items.removeIf { it.id == id }
    }
    // Update stock item :
    override fun updateStockItem(item : StockItem) {
        val index = items.indexOfFirst { it.id == item.id }
        if(index != -1)
            items[index] = item
    }
}