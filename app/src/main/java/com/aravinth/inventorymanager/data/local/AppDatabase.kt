package com.aravinth.inventorymanager.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aravinth.inventorymanager.domain.model.StockItem


@Database(
entities = [StockItem::class],
version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context):
                AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "inventory_db")
                .build()

                INSTANCE = instance
                instance
            }
        }
    }
}