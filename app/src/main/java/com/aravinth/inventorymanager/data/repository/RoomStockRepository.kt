package com.aravinth.inventorymanager.data.repository
import com.aravinth.inventorymanager.data.local.StockDao
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository

class RoomStockRepository (
    private val dao:StockDao): StockRepository{
    override  suspend fun addStockItem(item: StockItem) {
        //temporary
            dao.insert(item)
    }

    override suspend fun getAllStockItems(): List<StockItem> {
        return dao.getAll()
    }

    override suspend fun getStockItemById(id: Int): StockItem? {
        return dao.getById(id)
    }

    override suspend fun deleteStockItem(id: Int) {
        val item = dao.getById(id)
        if(item!=null) {
                dao.delete(item)
        }
    }

    override suspend fun updateStockItem(item: StockItem) {
            dao.update(item)
    }

    override suspend fun updateStockQuantity(id: Int, newQty: Int) {
        dao.updateStockQuantity(id, newQty)
    }
}