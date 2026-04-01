package com.aravinth.inventorymanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aravinth.inventorymanager.domain.model.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDAO {
    @Insert
    suspend fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM suppliers ORDER BY id DESC")
    fun getAllSuppliers(): Flow<List<Supplier>>
}