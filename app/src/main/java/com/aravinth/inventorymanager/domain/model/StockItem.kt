package com.aravinth.inventorymanager.domain.model

data class StockItem (
    val id: Int,
    val name: String,
    val purchasePrice: Double,
    val sellingPrice: Double,
    val quantity: Int,
    val reorderLevel: Int,
    val supplierId: String? = null
)
