package com.aravinth.inventorymanager.data.repository
import com.aravinth.inventorymanager.data.local.StockDao
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository

class RoomStockRepository (
    private val dao:StockDao): StockRepository{
    override fun addStockItem(item: StockItem) {
        //temporary
        Thread {
            dao.insert(item)
    }.start()
}

    override fun getAllStockItems(): List<StockItem> {
        return dao.getAll()
    }

    override fun getStockItemById(id: Int): StockItem? {
        return dao.getById(id)
    }

    override fun deleteStockItem(id: Int) {
        val item = dao.getById(id)
        if(item!=null) {
            Thread {
                dao.delete(item)
            }.start()
        }
    }

    override fun updateStockItem(item: StockItem) {
        Thread {
            dao.update(item)
        }.start()
    }
}