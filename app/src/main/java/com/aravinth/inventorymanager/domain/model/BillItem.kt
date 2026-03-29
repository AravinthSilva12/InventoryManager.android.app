package com.aravinth.inventorymanager.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_items")
data class BillItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val billId: Int = 0,
    val stockItemId: Int,
    val name: String,
    val sellingPrice: Double,
    val quantity: Int
)
@Entity(tableName = "bills")
data  class Bill(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val totalAmount: Double,
    val timestamp: Long
        )
