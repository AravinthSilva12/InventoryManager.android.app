package com.aravinth.inventorymanager.viewmodel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.aravinth.inventorymanager.data.repository.InMemoryStockRepository
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.usecase.StockUseCase

class StockViewModel: ViewModel() {

   // dependencies :

   companion object {private val repository = InMemoryStockRepository()}
   private val useCase = StockUseCase(repository)

   // UI state
   private val _items = mutableStateListOf<StockItem>()
   val items: List<StockItem> = _items

   // Initialization :
   init {
       loadItems()
   }

   // Internal functions :
    fun loadItems(){
    _items.clear()
    _items.addAll(useCase.getAllStockItems())
    }

   // CRUD :
   fun addStockItem(item: StockItem){
        useCase.addStock(item)
        loadItems()
   }

   fun updateStockItem(item: StockItem){
        useCase.updateStock(item)
        loadItems()
   }

   fun deleteStockItem(id: Int){
        useCase.deleteStock(id)
        loadItems()
   }

   // Filters :
   fun getInStockItems(): List<StockItem> {
        return items.filter { it.quantity > 0 }
   }

   fun getOutOfStockItems(): List<StockItem> {
        return items.filter { it.quantity == 0 }
   }

   fun getLowStockItems(): List<StockItem> {
       return items.filter{it.quantity <= it.reorderLevel}
   }

}