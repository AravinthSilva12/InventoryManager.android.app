package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.model.StockItem


interface BillRepository {
    fun addItem(item: BillItem)
    fun getBillItems(): List<BillItem>
    fun clearBill()
    fun getTotalAmount(): Double
}