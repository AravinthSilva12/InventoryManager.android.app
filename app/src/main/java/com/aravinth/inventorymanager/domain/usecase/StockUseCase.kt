package com.aravinth.inventorymanager.domain.usecase
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository

class StockUseCase (private val repository: StockRepository) {
    // add item :
    suspend fun addStock(item: StockItem) {
        repository.addStockItem(item)
    }
    // get all items :
    suspend fun getAllStockItems():List<StockItem> {
        return repository.getAllStockItems()
    }
    // get item by id :
    suspend fun getStockById(id: Int): StockItem? {
        return repository.getStockItemById(id)
    }
    // delete item :
    suspend fun deleteStock(id: Int){
        repository.deleteStockItem(id)
    }
    // update item :
    suspend fun updateStock(item: StockItem) {
        repository.updateStockItem(item)
    }
    // get low stock items :
    suspend fun getLowStockItems(): List<StockItem> {
     return repository.getAllStockItems().filter { it.quantity <= it.reorderLevel }
    }
    // get In stock items :
    suspend fun getInStockItems(): List<StockItem> {
        return repository.getAllStockItems().filter { it.quantity > 0 }
    }
    // get Out of stock items :
    suspend fun getOutOfStockItems(): List<StockItem> {
    return repository.getAllStockItems().filter { it.quantity == 0 }
    }
}