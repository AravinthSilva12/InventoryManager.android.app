package com.aravinth.inventorymanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BillDao {
    @Insert
    suspend fun insertBill(bill: Bill): Long

    @Insert
    suspend fun insertBillItems(items: List<BillItem>)

    @Query("SELECT * FROM bills ORDER BY timestamp DESC")
    fun getAllBills(): Flow<List<Bill>>
}