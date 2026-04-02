package com.aravinth.inventorymanager.domain.model
import java.util.Date

data class Crm(
    val customerId: Int = 0,
    val customerName: String,
    val customerPhone: String,
    val customerAddress: String,
    var purchaseDate: Date
)