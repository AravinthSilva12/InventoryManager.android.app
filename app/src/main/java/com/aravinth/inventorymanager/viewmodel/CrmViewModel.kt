package com.aravinth.inventorymanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.RoomCrmRepository
import com.aravinth.inventorymanager.domain.repository.CrmRepository
import com.aravinth.inventorymanager.domain.usecase.CrmUseCase
import kotlinx.coroutines.launch
import java.util.Date

class CrmViewModel(application: Application) : AndroidViewModel(application) {
     private val db = AppDatabase.getDatabase(application)
     private val repository: CrmRepository = RoomCrmRepository(db.crmDao())
     val useCase = CrmUseCase(repository)

     fun addCustomer(
          customerName: String,
          customerPhone: String,
          customerAddress: String,
          purchaseDate: Date
     ) {
          viewModelScope.launch {
               useCase.addCustomer(
                    customerName = customerName,
                    customerPhone = customerPhone,
                    customerAddress = customerAddress,
                    purchaseDate = purchaseDate)
          }
     }

     fun deleteCustomer(customerId: Int) {
          viewModelScope.launch {
               useCase.deleteCustomer(customerId)
          }
     }
}