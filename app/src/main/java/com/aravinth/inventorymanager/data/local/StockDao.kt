package com.aravinth.inventorymanager.data.local
import androidx.room.*
import com.aravinth.inventorymanager.domain.model.StockItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Insert
    suspend fun insert(item: StockItem)

    @Update
    suspend fun update(item: StockItem)

    @Delete
    suspend fun delete(item: StockItem)

    @Query("SELECT * FROM stock_items")
    fun getAll(): Flow<List<StockItem>>

    @Query("SELECT * FROM stock_items WHERE id = :id")
    suspend fun getById(id: Int): StockItem?

    @Query("SELECT*FROM stock_items WHERE id = :id")
    suspend fun getStockItemById(id:Int): StockItem?

    @Query("UPDATE stock_items SET quantity = :newQty WHERE id = :id")
    suspend fun updateStockQuantity(id:Int, newQty:Int)
}