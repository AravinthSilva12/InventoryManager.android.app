package com.aravinth.inventorymanager.domain.repository
import com.aravinth.inventorymanager.domain.model.StockItem

interface  StockRepository {
    suspend fun addStockItem(item: StockItem)
    suspend fun getAllStockItems(): List<StockItem>
    suspend fun getStockItemById(id: Int): StockItem?
    suspend fun deleteStockItem(id: Int)
    suspend fun updateStockItem(item: StockItem)

    suspend fun updateStockQuantity(id:Int, newQty:Int)
}