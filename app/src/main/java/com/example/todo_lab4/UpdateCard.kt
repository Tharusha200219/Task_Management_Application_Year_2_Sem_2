package com.example.todo_lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private var pos: Int = -1 // Initialize pos with a default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)
        updateButton = findViewById(R.id.update_button)
        deleteButton = findViewById(R.id.delete_button)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).addMigrations(myDatabase.MIGRATION_1_2)
            .build()


        pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            createTitle.setText(title)
            createPriority.setText(priority)

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            createTitle.text.toString(),
                            createPriority.text.toString(),
                            Date() // Pass the current date here
                        )
                    )
                }
                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTitle.text.toString(),
                    createPriority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1,
                            createTitle.text.toString(),
                            createPriority.text.toString(),
                            Date() // Pass the current date here
                        )
                    )
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
