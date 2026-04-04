package com.aravinth.inventorymanager.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aravinth.inventorymanager.domain.model.Bill
import com.aravinth.inventorymanager.domain.model.BillItem
import com.aravinth.inventorymanager.domain.model.Crm
import com.aravinth.inventorymanager.domain.model.StockItem
import com.aravinth.inventorymanager.domain.model.Supplier



@Database(
entities = [StockItem::class,
           Bill::class,
           BillItem::class,
           Supplier::class,
           Crm::class],
version = 4
)

@TypeConverters(Converters::class)

abstract class AppDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDao
    abstract fun billDao(): BillDao
    abstract fun supplierDao(): SupplierDAO

    abstract fun crmDao(): CrmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context):
                AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "inventory_db")
                    .fallbackToDestructiveMigration(true)
                .build()

                INSTANCE = instance
                instance
            }
        }
    }
}