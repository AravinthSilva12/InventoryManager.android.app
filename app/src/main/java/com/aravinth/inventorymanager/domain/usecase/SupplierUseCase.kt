package com.aravinth.inventorymanager.domain.usecase

import com.aravinth.inventorymanager.domain.model.Supplier
import com.aravinth.inventorymanager.domain.repository.SupplierRepository
import kotlinx.coroutines.flow.Flow

class SupplierUseCase(
    private val repository: SupplierRepository) {
    suspend fun addSupplier(
        name: String,
        phone: String,
        address: String
    ) {
        repository.addSupplier(
            Supplier(
                name = name,
                phone = phone,
                address = address
            )
        )
    }

    fun getSuppliers(): Flow<List<Supplier>> {
        return repository.getSuppliers()
    }
}