package com.aravinth.inventorymanager.domain.repository
import com.aravinth.inventorymanager.domain.model.StockItem
import kotlinx.coroutines.flow.Flow

interface  StockRepository {
    suspend fun addStockItem(item: StockItem)
    fun getAllStockItems(): Flow<List<StockItem>>
    suspend fun getStockItemById(id: Int): StockItem?
    suspend fun deleteStockItem(id: Int)
    suspend fun updateStockItem(item: StockItem)

    suspend fun updateStockQuantity(id:Int, newQty:Int)
}