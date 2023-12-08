package com.example.colorclash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private val easyButton by lazy { findViewById<Button>(R.id.easyButton) }
    private val hardButton by lazy { findViewById<Button>(R.id.hardButton) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        easyButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            intent.putExtra("DIFFICULTY", "Mjukstart")
            startActivity(intent)
        }

        hardButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            intent.putExtra("DIFFICULTY", "Hardcore")
            startActivity(intent)
        }
}
}