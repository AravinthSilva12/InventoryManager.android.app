package com.aravinth.inventorymanager.domain.usecase
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.repository.BillRepository
import com.aravinth.inventorymanager.domain.repository.StockRepository

class BillUseCase(private val repository: BillRepository, private val stockRepository: StockRepository)
{
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
    suspend fun generateBill(): Result<Bill> {
        val billItems = repository.getBillItems()
        val total = repository.getTotalAmount()

        for(billItem in billItems) {
            val stockItem = stockRepository.getStockItemById(billItem.stockItemId)

            if (stockItem != null) {
                val newQty = stockItem.quantity - billItem.quantity

                if (newQty < 0) {
                    return Result.failure(Exception("Not enough stock for ${stockItem.name}"))
                }

                stockRepository.updateStockQuantity(stockItem.id, newQty)
            }
        }

        return Result.success (
            Bill(
            id = 0,
            items = billItems,
            totalAmount = total,
            timestamp = System.currentTimeMillis() )
        )
    }
}
