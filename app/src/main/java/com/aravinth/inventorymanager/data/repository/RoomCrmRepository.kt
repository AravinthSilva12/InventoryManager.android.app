package com.aravinth.inventorymanager.data.repository

import com.aravinth.inventorymanager.data.local.CrmDao
import com.aravinth.inventorymanager.domain.model.Crm
import com.aravinth.inventorymanager.domain.repository.CrmRepository
import kotlinx.coroutines.flow.Flow

class RoomCrmRepository(private val crmDao: CrmDao) : CrmRepository {
    override fun addCustomer(customer: Crm) {
       crmDao.insertCustomer(customer)
    }

    override fun getCustomer(): Flow<List<Crm>> {
        return crmDao.getAllCustomers()
    }

    override fun deleteCustomer(customerId: Crm) {
       crmDao.deleteCustomer(customerId)
    }

}