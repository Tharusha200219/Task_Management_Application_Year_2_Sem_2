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

// Activity for creating a new task
class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var createDate: DatePicker
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        // Initialize views
        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)
        saveButton = findViewById(R.id.save_button)
        createDate = findViewById(R.id.create_date)

        // Button for navigating back to MainActivity
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this@CreateCard, MainActivity::class.java)
            startActivity(intent)
        }

        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).addMigrations(myDatabase.MIGRATION_1_2)
            .build()

        // Save button onClickListener
        saveButton.setOnClickListener {
            val title = createTitle.text.toString().trim()
            val priority = createPriority.text.toString().trim()
            val date = Date(createDate.year - 1900, createDate.month, createDate.dayOfMonth)

            // Ensure title and priority are not empty
            if (title.isNotEmpty() && priority.isNotEmpty()) {
                // Save data to DataObject for temporary storage
                DataObject.setData(title, priority, date)

                // Insert task into Room database
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, date))
                }

                // Navigate back to MainActivity
                val intent = Intent(this@CreateCard, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
