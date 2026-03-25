package com.aravinth.inventorymanager.viewmodel
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.aravinth.inventorymanager.data.repository.InMemoryBillRepository
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.usecase.BillUseCase

class BillViewModel(application: Application) :
    AndroidViewModel(application) {
    //Dependencies:
    private val repository = InMemoryBillRepository()
    private val useCase = BillUseCase(repository)

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


    fun generateBill(): Bill {
            val bill = useCase.generateBill()
            clearBill()
            return bill
    }
}
