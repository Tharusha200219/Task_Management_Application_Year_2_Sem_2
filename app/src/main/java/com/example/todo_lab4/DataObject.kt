package com.example.todo_lab4

import java.util.Date

// Singleton object for managing task data
object DataObject {
    // List to store task data
    var listdata = mutableListOf<CardInfo>()

    // Method to add new task data to the list
    fun setData(title: String, priority: String, date: Date) {
        listdata.add(CardInfo(title, priority, date))
    }

    // Method to retrieve all task data from the list
    fun getAllData(): List<CardInfo> {
        return listdata
    }

    // Method to delete all task data from the list
    fun deleteAll() {
        listdata.clear()
    }

    // Method to retrieve task data at a specific position in the list
    fun getData(pos: Int): CardInfo {
        return listdata[pos]
    }

    // Method to delete task data at a specific position in the list
    fun deleteData(pos: Int) {
        listdata.removeAt(pos)
    }

    // Method to update task data at a specific position in the list
    fun updateData(pos: Int, title: String, priority: String) {
        listdata[pos].title = title
        listdata[pos].priority = priority
    }
}
