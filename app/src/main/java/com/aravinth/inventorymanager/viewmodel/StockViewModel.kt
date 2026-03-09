package com.aravinth.inventorymanager.viewmodel
import androidx.lifecycle.ViewModel
import com.aravinth.inventorymanager.data.repository.InMemoryStockRepository
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.usecase.StockUseCase

class StockViewModel: ViewModel() {
   private val repository = InMemoryStockRepository()
   private val useCase = StockUseCase(repository)

   fun getAllStockItems(): List<StockItem> {
        return useCase.getAllStockItems()
   }

   fun getLowStockItems(): List<StockItem> {
        return useCase.getLowStockItems()
   }

   fun addStockItem(item: StockItem){
        useCase.addStock(item)
   }

   fun deleteStockItem(id: Int){
        useCase.deleteStock(id)
   }

   fun updateStockItem(item: StockItem){
       useCase.updateStock(item)
   }

   fun getInStockItems(): List<StockItem> {
        return useCase.getInStockItems()
   }

   fun getOutOfStockItems(): List<StockItem> {
       return useCase.getOutOfStockItems()
   }
}