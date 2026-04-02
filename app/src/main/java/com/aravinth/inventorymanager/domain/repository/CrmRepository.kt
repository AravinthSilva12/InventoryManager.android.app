package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.Crm
import kotlinx.coroutines.flow.Flow

interface CrmRepository {
    fun addCustomer(customer: Crm)
    fun getCustomer(): Flow<List<Crm>>
    fun deleteCustomer(customerId: Crm)
}