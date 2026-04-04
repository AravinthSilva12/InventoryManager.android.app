package com.aravinth.inventorymanager.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aravinth.inventorymanager.domain.model.Crm
import kotlinx.coroutines.flow.Flow

@Dao
interface CrmDao {
  @Insert
  suspend fun insertCustomer(crm: Crm)

  @Query("SELECT * FROM Crm ORDER BY customerId DESC")
  fun getAllCustomers(): Flow<List<Crm>>

  @Query("DELETE FROM Crm WHERE customerId = :customerId")
  suspend fun deleteCustomer(customerId: Int)
}