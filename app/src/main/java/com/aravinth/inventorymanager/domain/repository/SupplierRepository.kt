package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.Supplier
import kotlinx.coroutines.flow.Flow

interface SupplierRepository {
    suspend fun addSupplier(supplier: Supplier)
    fun getSuppliers(): Flow<List<Supplier>>
}