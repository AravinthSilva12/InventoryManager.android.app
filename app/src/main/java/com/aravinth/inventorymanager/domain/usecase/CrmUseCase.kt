package com.aravinth.inventorymanager.domain.usecase

import com.aravinth.inventorymanager.domain.model.Crm
import com.aravinth.inventorymanager.domain.repository.CrmRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date

class CrmUseCase(private val repository: CrmRepository) {
   suspend fun addCustomer(
       customerName: String,
       customerPhone: String,
       customerAddress: String,
       purchaseDate: Date
   ) {
       repository.addCustomer(
           Crm(
               customerName = customerName,
               customerPhone = customerPhone,
               customerAddress = customerAddress,
               purchaseDate = purchaseDate
           )
       )
    }

   suspend fun getCustomer(): Flow<List<Crm>> {
       return repository.getCustomer()
    }

   suspend fun deleteCustomer(customerId: Int){
     repository.deleteCustomer(customerId)
    }
}
