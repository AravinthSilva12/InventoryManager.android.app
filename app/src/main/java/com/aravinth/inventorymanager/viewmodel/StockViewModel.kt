package com.aravinth.inventorymanager.viewmodel
import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.RoomStockRepository
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.repository.StockRepository
import com.aravinth.inventorymanager.domain.usecase.StockUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StockViewModel(application: Application) : AndroidViewModel(application) {

    // dependencies
    private val repository: StockRepository
    private val useCase: StockUseCase

    // UI state (CORRECT)
    private val _items = mutableStateListOf<StockItem>()
    val items: List<StockItem> get() = _items

    // Initialization
    init {
        val db = AppDatabase.getDatabase(application)
        repository = RoomStockRepository(db.stockDao())
        useCase = StockUseCase(repository)
        loadItems()
    }

    // Load items
    fun loadItems() {
        viewModelScope.launch {
            val result = useCase.getAllStockItems().first()
            _items.clear()
            _items.addAll(result)
        }
    }

    // CRUD
    fun addStockItem(item: StockItem) {
        viewModelScope.launch {
            useCase.addStock(item)
            loadItems()
        }
    }

    fun updateStockItem(item: StockItem) {
        viewModelScope.launch {
            useCase.updateStock(item)
            loadItems()
        }
    }

    fun deleteStockItem(id: Int) {
        viewModelScope.launch {
            useCase.deleteStock(id)
            loadItems()
        }
    }

    // Filters (NOW WORKS)
    fun getInStockItems(): List<StockItem> {
        return items.filter { it.quantity > 0 }
    }

    fun getOutOfStockItems(): List<StockItem> {
        return items.filter { it.quantity == 0 }
    }

    fun getLowStockItems(): List<StockItem> {
        return items.filter { it.quantity <= it.reorderLevel }
    }
}