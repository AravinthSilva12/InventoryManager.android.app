package com.aravinth.inventorymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.RoomSupplierRepository
import com.aravinth.inventorymanager.domain.model.Supplier
import com.aravinth.inventorymanager.domain.repository.SupplierRepository
import kotlinx.coroutines.launch

class SupplierViewModel(application: Application):
   AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val repository: SupplierRepository = RoomSupplierRepository(db.supplierDao())

    val suppliers = repository.getSuppliers()

    fun addSupplier(name: String, phone: String, address: String) {
        viewModelScope.launch { repository.addSupplier(
            Supplier(
                name = name,
                phone = phone,
                address = address
            )
        )
        }
    }
}