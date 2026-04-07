package com.aravinth.inventorymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.RoomBillRepository
import com.aravinth.inventorymanager.data.repository.RoomCrmRepository
import com.aravinth.inventorymanager.data.repository.RoomStockRepository
import com.aravinth.inventorymanager.domain.repository.BillHistoryRepository
import com.aravinth.inventorymanager.domain.repository.CrmRepository
import com.aravinth.inventorymanager.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
       private val stockRepo: StockRepository = RoomStockRepository(db.stockDao())
       private val billHistoryRepo: BillHistoryRepository = RoomBillRepository(db.billDao())
       private val crmRepo: CrmRepository = RoomCrmRepository(db.crmDao())

        val totalStockItems: Flow<Int> =  stockRepo.getAllStockItems().map { list -> list.size}

        val totalSales: Flow<Double> = billHistoryRepo.getAllBills().map { list -> list.sumOf { it.totalAmount }}

        val totalCustomers: Flow<Int> = crmRepo.getCustomer().map { list -> list.size}

        val lowStockCount: Flow<Int> = stockRepo.getAllStockItems().map { list -> list.count{it.quantity <= it.reorderLevel} }
}
