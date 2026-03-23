package com.aravinth.inventorymanager.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "bill_items")
data class BillModel(
@PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    val name: String,
    val purchasePrice: Double,
    val sellingPrice: Double,
    val quantity: Int,
    val total: Double
)
