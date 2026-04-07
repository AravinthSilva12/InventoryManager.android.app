package com.aravinth.inventorymanager.domain.repository

import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import kotlinx.coroutines.flow.Flow

interface BillHistoryRepository {
    suspend fun insertBillAndReturnId(bill: Bill): Int

    suspend fun insertBillItems(items: List<BillItem>)

    fun getAllBills(): Flow<List<Bill>>

    fun getBillItemsByBillId(billId: Int): Flow<List<BillItem>>

}