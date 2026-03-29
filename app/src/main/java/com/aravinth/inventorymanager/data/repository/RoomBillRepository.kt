package com.aravinth.inventorymanager.data.repository

import  com.aravinth.inventorymanager.data.local.BillDao
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.repository.BillHistoryRepository
import com.aravinth.inventorymanager.domain.repository.BillRepository
import kotlinx.coroutines.flow.Flow


class RoomBillRepository(private val billDao: BillDao) : BillHistoryRepository {

  override suspend fun insertBillAndReturnId(bill: Bill): Int{
        return billDao.insertBill(bill).toInt()
    }

  override suspend fun insertBillItems(items: List<BillItem>) {
        billDao.insertBillItems(items)
    }

    override fun getAllBills(): Flow<List<Bill>> {
        return billDao.getAllBills()
    }
}