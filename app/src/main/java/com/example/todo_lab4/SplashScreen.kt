package com.example.todo_lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).addMigrations(myDatabase.MIGRATION_1_2)
            .build()

        // Find the button by its ID
        val continueButton: Button = findViewById(R.id.continue_button)

        // Set OnClickListener for the button
        continueButton.setOnClickListener {
            loadDataAndStartMainActivity()
        }
    }

    private fun loadDataAndStartMainActivity() {
        GlobalScope.launch {
            DataObject.listdata = database.dao().getTasks() as MutableList<CardInfo>
            // Create an intent to start MainActivity
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            // Finish the current activity to prevent going back to SplashScreen
            finish()
        }
    }
}
