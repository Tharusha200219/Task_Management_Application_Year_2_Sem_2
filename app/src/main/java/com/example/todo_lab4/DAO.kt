package com.example.todo_lab4

import androidx.room.*

// Data Access Object (DAO) interface for Room database operations
@Dao
interface DAO {
    // Insert a new task into the database
    @Insert
    suspend fun insertTask(entity: Entity)

    // Update an existing task in the database
    @Update
    suspend fun updateTask(entity: Entity)

    // Delete a task from the database
    @Delete
    suspend fun deleteTask(task: Entity)

    // Delete all tasks from the database
    @Query("DELETE FROM to_do")
    suspend fun deleteAll()

    // Retrieve all tasks from the database
    @Query("SELECT * FROM to_do")
    suspend fun getTasks(): List<CardInfo>
}
