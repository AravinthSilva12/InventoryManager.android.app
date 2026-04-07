package com.aravinth.inventorymanager.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `bills` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `totalAmount` REAL NOT NULL,
                `timestamp` INTEGER NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `bill_items` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `billId` INTEGER NOT NULL,
                `stockItemId` INTEGER NOT NULL,
                `name` TEXT NOT NULL,
                `sellingPrice` REAL NOT NULL,
                `quantity` INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `suppliers` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `phone` TEXT NOT NULL,
                `address` TEXT NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `Crm` (
                `customerId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `customerName` TEXT NOT NULL,
                `customerPhone` TEXT NOT NULL,
                `customerAddress` TEXT NOT NULL,
                `purchaseDate` INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        if (!hasColumn(db, tableName = "stock_items", columnName = "supplierId")) {
            db.execSQL("ALTER TABLE `stock_items` ADD COLUMN `supplierId` TEXT")
        }
    }
}

private fun hasColumn(db: SupportSQLiteDatabase, tableName: String, columnName: String): Boolean {
    db.query("PRAGMA table_info(`$tableName`)").use { cursor ->
        val nameIndex = cursor.getColumnIndex("name")
        while (cursor.moveToNext()) {
            if (cursor.getString(nameIndex) == columnName) {
                return true
            }
        }
    }
    return false
}
