package com.example.todo_lab4

import androidx.room.TypeConverter
import java.util.Date

// Converter class to convert between Date objects and timestamps for Room database
class Converters {

    // Convert timestamp to Date object
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    // Convert Date object to timestamp
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
