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

  @Query("SELECT * FROM CRM ORDER BY id DESC")
  suspend fun getAllCustomers(): Flow<List<Crm>>

  @Delete
  suspend fun deleteCustomer(customerId: Crm)

}