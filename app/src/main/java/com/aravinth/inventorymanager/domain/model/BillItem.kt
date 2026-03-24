package com.aravinth.inventorymanager.domain.model
import androidx.room.PrimaryKey

data class BillItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val stockItemId: Int,
    val name: String,
    val sellingPrice: Double,
    val quantity: Int
)

data  class Bill(
    val id: Int = 0,
    val items: List<BillItem>,
    val totalAmount: Double,
    val timestamp: Long
        )
