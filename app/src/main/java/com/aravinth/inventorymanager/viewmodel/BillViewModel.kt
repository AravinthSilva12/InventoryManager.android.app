package com.aravinth.inventorymanager.viewmodel
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aravinth.inventorymanager.data.local.AppDatabase
import com.aravinth.inventorymanager.data.repository.AppContainer
import com.aravinth.inventorymanager.data.repository.InMemoryBillRepository
import com.aravinth.inventorymanager.data.repository.InMemoryStockRepository
import com.aravinth.inventorymanager.data.repository.RoomBillRepository
import com.aravinth.inventorymanager.data.repository.RoomStockRepository
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.usecase.BillUseCase
import com.aravinth.inventorymanager.domain.usecase.StockUseCase
import kotlinx.coroutines.launch

class BillViewModel(application: Application) :
    AndroidViewModel(application) {
    //Dependencies:
    private val repository = AppContainer.billRepository
    private val db = AppDatabase.getDatabase(application)


    private val stockRepository = RoomStockRepository(db.stockDao())
    private val historyRepository = RoomBillRepository(db.billDao())

    private val useCase = BillUseCase(repository,  historyRepository, stockRepository)

    val bills = historyRepository.getAllBills()

    private var _errorMessage by mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage

    //UI state:
    private val _items = mutableStateListOf<BillItem>()
    val items: List<BillItem> get() = _items
    private var _total by mutableDoubleStateOf(0.0)
    val total: Double get() = _total

    //Initialize:
    init {
        loadItems()
    }

    //Load:
    fun loadItems(){
        val result = useCase.getBillItems()
        _items.clear()
        _items.addAll(result)
        _total = useCase.getTotal()
    }

    //CRUD:
    fun addItem(item: BillItem) {
            useCase.addItem(item)
        loadItems()
    }

    fun clearBill() {
            useCase.clearBill()
         loadItems()
    }


    fun generateBill() {
        viewModelScope.launch {
            val result = useCase.generateBill()
            if(result.isSuccess) {
                clearBill()
            } else {
                _errorMessage = result.exceptionOrNull()?.message
            }
        }
    }
}
