package com.aravinth.inventorymanager.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converters {

    @TypeConverters
    fun fromTimeStamp(value: Long?): Date? =
        value?.let { Date(it) }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? =
        date?.time
}
