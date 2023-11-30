package com.example.colorclash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var  hardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        easyButton = findViewById(R.id.easyButton)
        hardButton = findViewById(R.id.hardButton)

        easyButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            intent.putExtra("DIFFICULTY", "easy") // Lägg till "easy" som svårighetsgrad
            startActivity(intent)
        }

        hardButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            intent.putExtra("DIFFICULTY", "hard") // Lägg till "hard"" som svårighetsgrad
            startActivity(intent)
        }
}
}