package com.aravinth.inventorymanager.data.repository

import com.aravinth.inventorymanager.data.local.SupplierDAO
import com.aravinth.inventorymanager.domain.model.Supplier
import com.aravinth.inventorymanager.domain.repository.SupplierRepository
import kotlinx.coroutines.flow.Flow

class RoomSupplierRepository(private val supplierDao: SupplierDAO) :
    SupplierRepository {
   override suspend fun addSupplier(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getSuppliers(): Flow<List<Supplier>> {
        return supplierDao.getAllSuppliers()
    }
}