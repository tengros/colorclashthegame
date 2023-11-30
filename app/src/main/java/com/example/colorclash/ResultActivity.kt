package com.example.colorclash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var playAgainButton: Button
    private lateinit var exitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        playAgainButton = findViewById(R.id.playAgainButton)
        exitButton = findViewById(R.id.exitButton)

        // Hämta highscore från SharedPreferences
        val sharedPreferences = getSharedPreferences("HighscorePrefs", Context.MODE_PRIVATE)
        val highscore = sharedPreferences.getInt("HIGHSCORE", 0)

        // Hitta highscoreTextView i layouten och sätt highscore-värdet
        val highscoreTextView: TextView = findViewById(R.id.highscoreTextView)
        highscoreTextView.text = "Highscore: $highscore"

        // Hämta den totala poängen från extras om den finns, annars sätt till ett standardvärde
        val totalScore = intent.getIntExtra("TOTAL_SCORE", 0)

        // Visa den totala poängen, t.ex. genom att uppdatera en TextView
        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        scoreTextView.text = "Total poäng: $totalScore"

        exitButton.setOnClickListener {
            finishAffinity()
        }

        playAgainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

}