// CreateCard.kt
package com.example.todo_lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var createDate: DatePicker
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)
        saveButton = findViewById(R.id.save_button)
        createDate = findViewById(R.id.create_date)

        val backButton: Button = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this@CreateCard, MainActivity::class.java)
            startActivity(intent)
        }

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).addMigrations(myDatabase.MIGRATION_1_2)
            .build()




        saveButton.setOnClickListener {
            val title = createTitle.text.toString().trim()
            val priority = createPriority.text.toString().trim()
            val date = Date(createDate.year - 1900, createDate.month, createDate.dayOfMonth)

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                DataObject.setData(title, priority, date)

                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, date))
                }

                val intent = Intent(this@CreateCard, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
