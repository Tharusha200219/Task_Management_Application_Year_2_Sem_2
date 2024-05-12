package com.example.todo_lab4

import java.util.Date


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String, date: Date) {
        listdata.add(CardInfo(title, priority,date ))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
    }

}