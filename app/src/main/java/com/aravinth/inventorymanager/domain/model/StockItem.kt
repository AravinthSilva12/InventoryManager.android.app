package com.aravinth.inventorymanager.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "stock_items")
data class StockItem (
    @PrimaryKey
    var id: Int = 0,

    val name: String,
    val purchasePrice: Double,
    val sellingPrice: Double,
    val quantity: Int,
    val reorderLevel: Int,
    val supplierId: String? = null
)
