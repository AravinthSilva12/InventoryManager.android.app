package com.aravinth.inventorymanager.domain.usecase
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository

class StockUseCase (private val repository: StockRepository) {
    // add item :
    fun addStock(item: StockItem) {
        repository.addStockItem(item)
    }
    // get all items :
    fun getAllStockItems():List<StockItem> {
        return repository.getAllStockItems()
    }
    // get item by id :
    fun getStockById(id: Int): StockItem? {
        return repository.getStockItemById(id)
    }
    // delete item :
    fun deleteStock(id: Int){
        repository.deleteStockItem(id)
    }
    // update item :
    fun updateStock(item: StockItem) {
        repository.updateStockItem(item)
    }
    // get low stock items :
    fun getLowStockItems(): List<StockItem> {
     return repository.getAllStockItems().filter { it.quantity <= it.reorderLevel }
    }
    // get In stock items :
    fun getInStockItems(): List<StockItem> {
        return repository.getAllStockItems().filter { it.quantity > 0 }
    }
    // get Out of stock items :
    fun getOutOfStockItems(): List<StockItem> {
    return repository.getAllStockItems().filter { it.quantity == 0 }
    }
}