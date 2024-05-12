package com.example.todo_lab4


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Entity::class], version = 2)
@TypeConverters(Converters::class)
abstract class myDatabase : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE To_Do ADD COLUMN date INTEGER NOT NULL DEFAULT 0")
            }
        }

    }
}
