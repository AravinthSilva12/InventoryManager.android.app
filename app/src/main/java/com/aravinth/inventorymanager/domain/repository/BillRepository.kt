package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.BillItem

interface BillRepository {
    fun addItem(item: BillItem)
    fun getBillItems(): List<BillItem>
    fun clearBill()
    fun getTotalAmount(): Double
}