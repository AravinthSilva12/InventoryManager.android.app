package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.Crm
import kotlinx.coroutines.flow.Flow

interface CrmRepository {
    suspend fun addCustomer(customer: Crm)
    suspend fun getCustomer(): Flow<List<Crm>>
    suspend fun deleteCustomer(customerId: Int)
}