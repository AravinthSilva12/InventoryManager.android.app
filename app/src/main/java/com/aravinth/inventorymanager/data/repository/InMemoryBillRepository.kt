package com.aravinth.inventorymanager.data.repository

import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.repository.BillRepository

class InMemoryBillRepository : BillRepository {

    private val itemList = mutableListOf<BillItem>()

    //Add item:
    override fun addItem(item: BillItem){
        itemList.add(item)
    }

    //Retrieve bill items:
    override fun getBillItems(): List<BillItem> {
        return itemList
    }

    //Clear bill:
    override fun clearBill(){
        itemList.clear()
    }

    //Get total amount:
    override fun getTotalAmount(): Double {
        return itemList.sumOf {it.sellingPrice*it.quantity}
    }
}