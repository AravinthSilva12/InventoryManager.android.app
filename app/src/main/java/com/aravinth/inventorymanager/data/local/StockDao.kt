package com.aravinth.inventorymanager.data.local
import androidx.room.*
import com.aravinth.inventorymanager.domain.model.StockItem

@Dao
interface StockDao {
    @Insert
    suspend fun insert(item: StockItem)

    @Update
    suspend fun update(item: StockItem)

    @Delete
    suspend fun delete(item: StockItem)

    @Query("SELECT * FROM stock_items")
    suspend fun getAll():List<StockItem>

    @Query("SELECT * FROM stock_items WHERE id = :id")
    suspend fun getById(id: Int): StockItem?
}