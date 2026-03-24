package com.aravinth.inventorymanager.domain.usecase
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.repository.BillRepository

class BillUseCase(private val repository: BillRepository) {

    //Add Bill Item:
    fun addItem(item: BillItem){
      repository.addItem(item)
    }

    //Get Bill item list:
    fun getBillItems(): List<BillItem> {
        return repository.getBillItems()
    }

    //Clear bill list:
    fun clearBill(){
        repository.clearBill()
    }

    //Get total amount of the bill:
    fun getTotal(): Double {
       return repository.getTotalAmount()
    }

    //Generate Bill:
    fun generateBill(): Bill {
        val billItems = repository.getBillItems()
        val total = repository.getTotalAmount()
        return Bill (
            id = 0,
            items = billItems,
            totalAmount = total,
            timestamp = System.currentTimeMillis() )
        }
    }
