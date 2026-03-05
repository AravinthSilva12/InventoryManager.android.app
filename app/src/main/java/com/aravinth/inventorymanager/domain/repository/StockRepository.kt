package com.aravinth.inventorymanager.domain.repository
import com.aravinth.inventorymanager.domain.model.StockItem

interface  StockRepository {
    fun addStockItem(item: StockItem)
    fun getAllStockItems(): List<StockItem>
    fun getStockItemById(id: Int): StockItem?
    fun deleteStockItem(id: Int)
    fun updateStockItem(item: StockItem)
}