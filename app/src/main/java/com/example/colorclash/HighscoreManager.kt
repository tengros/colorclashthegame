package com.example.colorclash

import android.content.Context
import android.util.Log

object HighscoreManager {
    fun loadHighscore(context: Context, difficulty: String): Int {
        val sharedPreferences = context.getSharedPreferences("HighscorePrefs_$difficulty", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("HIGHSCORE_$difficulty", 0)
    }

    fun saveHighscore(context: Context, difficulty: String, score: Int) {
        Log.d("HighscoreManager", "Saving highscore for $difficulty with score $score")
        val sharedPreferences = context.getSharedPreferences("HighscorePrefs_$difficulty", Context.MODE_PRIVATE)
        val highscoreKey = "HIGHSCORE_$difficulty"
        val highscore = sharedPreferences.getInt(highscoreKey, 0)

        if (score > highscore) {
            val editor = sharedPreferences.edit()
            editor.putInt(highscoreKey, score)
            editor.apply() // Säkerställ att ändringarna sparas
        }
    }

    fun saveHighscoreIfHigher(context: Context, score: Int, difficulty: String) {
        Log.d("HighscoreManager", "Checking highscore for $difficulty with score $score")
        val highscore = loadHighscore(context, difficulty)
        if (score > highscore) {
            saveHighscore(context, difficulty, score)
        }
    }
}

