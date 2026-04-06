package com.aravinth.inventorymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.RoomBillRepository
import com.aravinth.inventorymanager.data.repository.RoomCrmRepository
import com.aravinth.inventorymanager.data.repository.RoomStockRepository
import com.aravinth.inventorymanager.data.repository.RoomSupplierRepository
import com.aravinth.inventorymanager.domain.repository.BillHistoryRepository
import com.aravinth.inventorymanager.domain.repository.BillRepository
import com.aravinth.inventorymanager.domain.repository.CrmRepository
import com.aravinth.inventorymanager.domain.repository.StockRepository
import com.aravinth.inventorymanager.domain.repository.SupplierRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {


    private val db = AppDatabase.getDatabase(application)

       private val stockRepo: StockRepository = RoomStockRepository(db.stockDao())
       private val billRepo: BillHistoryRepository = RoomBillRepository(db.billDao())
       private val supplierRepo: SupplierRepository = RoomSupplierRepository(db.supplierDao())
       private val crmRepo: CrmRepository = RoomCrmRepository(db.crmDao())


}