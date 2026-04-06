package com.aravinth.inventorymanager.domain.usecase
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StockUseCase (private val repository: StockRepository) {
    // add item :
    suspend fun addStock(item: StockItem) {
        repository.addStockItem(item)
    }

    // get all items :
    fun getAllStockItems(): Flow<List<StockItem>> {
        return repository.getAllStockItems()
    }

    // get item by id :
    suspend fun getStockById(id: Int): StockItem? {
        return repository.getStockItemById(id)
    }

    // delete item :
    suspend fun deleteStock(id: Int) {
        repository.deleteStockItem(id)
    }

    // update item :
    suspend fun updateStock(item: StockItem) {
        repository.updateStockItem(item)
    }

    // get low stock items :
    fun getLowStockItems(): Flow<List<StockItem>> {
        return repository.getAllStockItems()
            .map { list -> list.filter { it.quantity <= it.reorderLevel } }
    }

    // get In stock items :
    fun getInStockItems(): Flow<List<StockItem>> {
        return repository.getAllStockItems().map { list -> list.filter { it.quantity > 0 } }

    }
}
