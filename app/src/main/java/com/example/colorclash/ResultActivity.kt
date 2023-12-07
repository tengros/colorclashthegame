package com.example.colorclash
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

        val totalScore = intent.getIntExtra("TOTAL_SCORE", 0)
        val difficulty = intent.getStringExtra("DIFFICULTY")

        HighscoreManager.saveHighscoreIfHigher(this, difficulty ?: "Mjukstart", totalScore)

        val highscore = HighscoreManager.loadHighscore(this, difficulty ?: "Mjukstart")
        if (totalScore > highscore) {
            HighscoreManager.saveHighscoreIfHigher(this, difficulty ?: "Mjukstart", totalScore, )
        }

        playAgainButton = findViewById(R.id.playAgainButton)
        exitButton = findViewById(R.id.exitButton)

        val highscoreTextView: TextView = findViewById(R.id.highscoreTextView)
        highscoreTextView.text = "$difficulty highscore: $highscore"

// Visa den totala poängen, t.ex. genom att uppdatera en TextView
        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        scoreTextView.text = "Total poäng: $totalScore"

        exitButton.setOnClickListener {
            finishAffinity()
        }

        playAgainButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}