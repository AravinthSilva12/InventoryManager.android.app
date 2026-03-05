package com.aravinth.inventorymanager.domain.model

data class StockItem (
    var id: Int,
    val name: String,
    val purchasePrice: Double,
    val sellingPrice: Double,
    val quantity: Int,
    val reorderLevel: Int,
    val supplierId: String? = null
)
