package com.example.colorclash


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val cards = listOf(
        "hearts_2", "hearts_3", "hearts_4", "hearts_5", "hearts_6", "hearts_7", "hearts_8", "hearts_9", "hearts_10", "hearts_jack", "hearts_queen", "hearts_king", "hearts_ace",
        "spades_2", "spades_3", "spades_4", "spades_5", "spades_6", "spades_7", "spades_8", "spades_9", "spades_10", "spades_jack", "spades_queen", "spades_king", "spades_ace",
        "diamonds_2", "diamonds_3", "diamonds_4", "diamonds_5", "diamonds_6", "diamonds_7", "diamonds_8", "diamonds_9", "diamonds_10", "diamonds_jack", "diamonds_queen", "diamonds_king", "diamonds_ace",
        "clubs_2", "clubs_3", "clubs_4", "clubs_5", "clubs_6", "clubs_7", "clubs_8", "clubs_9", "clubs_10", "clubs_jack", "clubs_queen", "clubs_king", "clubs_ace"
    )

    private var selectedColor: String = ""

    private lateinit var currentCard: ImageView
    private lateinit var blackButton: Button
    private lateinit var redButton: Button
    private lateinit var scoreView: TextView
    private lateinit var spadesButton: Button
    private lateinit var clubsButton: Button
    private lateinit var heartsButton: Button
    private lateinit var diamondsButton: Button
    private var difficulty: String? = null

    private var score = 0
    private var consecutiveCorrectGuesses = 0
    private var totalGuesses = 0

    private val colorValues = mapOf(
        "hearts" to "red",
        "diamonds" to "red",
        "spades" to "black",
        "clubs" to "black"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        difficulty = intent.getStringExtra("DIFFICULTY")


        currentCard = findViewById(R.id.currentCard)
        blackButton = findViewById(R.id.blackButton)
        redButton = findViewById(R.id.redButton)
        scoreView = findViewById(R.id.scoreView)
        spadesButton = findViewById(R.id.spadesButton)
        clubsButton = findViewById(R.id.clubsButton)
        heartsButton = findViewById(R.id.heartsButton)
        diamondsButton = findViewById(R.id.diamondsButton)


        if (difficulty == "Hardcore") {

            spadesButton.visibility = View.VISIBLE
            clubsButton.visibility = View.VISIBLE
            heartsButton.visibility = View.VISIBLE
            diamondsButton.visibility = View.VISIBLE
            redButton.visibility = View.GONE
            blackButton.visibility = View.GONE

        } else if (difficulty == "Mjukstart"){

            redButton.visibility = View.VISIBLE
            blackButton.visibility = View.VISIBLE
            spadesButton.visibility = View.GONE
            clubsButton.visibility = View.GONE
            heartsButton.visibility = View.GONE
            diamondsButton.visibility = View.GONE
        }

        heartsButton.setOnClickListener {
            selectedColor = "hearts"
            showRandomCard()
            totalGuesses++
        }

        spadesButton.setOnClickListener {
            selectedColor = "spades"
            showRandomCard()
            totalGuesses++
        }

        clubsButton.setOnClickListener {
            selectedColor = "clubs"
            showRandomCard()
            totalGuesses++
        }

        diamondsButton.setOnClickListener {
            selectedColor = "diamonds"
            showRandomCard()
            totalGuesses++
        }

        redButton.setOnClickListener {
            selectedColor = "red"
            showRandomCard()
            totalGuesses++
        }

        blackButton.setOnClickListener {
            selectedColor = "black"
            showRandomCard()
            totalGuesses++
        }

    }

    private fun showRandomCard() {
        val randomPosition = Random.nextInt(cards.size)
        val randomCard = cards[randomPosition]

        val color = randomCard.substring(0, randomCard.indexOf("_"))
        val cardColor = colorValues[color] ?: ""

        if (cardColor == selectedColor ||  color == selectedColor)   {
            if (consecutiveCorrectGuesses == 0) {
                score += 1 // Första gången får man 1 poäng
            } else {
                score += 2.0.pow(consecutiveCorrectGuesses).toInt() // Öka poängen med en dubbling från andra gissningen
            }
            consecutiveCorrectGuesses++
        } else {
            consecutiveCorrectGuesses = 0 // Återställ räknaren om gissningen är fel
        }
        updateScore()

        val resourceId = resources.getIdentifier(randomCard, "drawable", packageName)
        currentCard.setImageResource(resourceId)


        if (totalGuesses == 15) {
            com.example.colorclash.HighscoreManager.saveHighscoreIfHigher(this,difficulty ?: "Mjukstart", score)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("TOTAL_SCORE", score)
            intent.putExtra("DIFFICULTY", difficulty)
            startActivity(intent)
        }


    }
    private fun updateScore() {
        scoreView.text = "Poäng: $score"
    }
}


//fun resetHighscore() {
//    val sharedPreferences = getSharedPreferences("HighscorePrefs", Context.MODE_PRIVATE)
//    val editor = sharedPreferences.edit()
//    editor.putInt("HIGHSCORE", 0) // Sätt highscore till noll
//    editor.apply()
//}