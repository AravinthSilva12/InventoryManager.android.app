package com.aravinth.inventorymanager.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "Crm")
data class Crm(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val customerName: String,
    val customerPhone: String,
    val customerAddress: String,
    var purchaseDate: Date
)