package com.example.colorclash

import android.content.Context
import android.util.Log

object HighscoreManager {
    fun loadHighscore(context: Context, difficulty: String): Int {
        val sharedPreferences = context.getSharedPreferences("HighscorePrefs_$difficulty", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("HIGHSCORE_$difficulty", 0)
    }



    fun saveHighscoreIfHigher(context: Context, difficulty: String, score: Int) {
        val sharedPreferences = context.getSharedPreferences("HighscorePrefs_$difficulty", Context.MODE_PRIVATE)
        val highscoreKey = "HIGHSCORE_$difficulty"
        val highscore = sharedPreferences.getInt(highscoreKey, 0)

        if (score > highscore) {
            val editor = sharedPreferences.edit()
            editor.putInt(highscoreKey, score)
            editor.apply()
        }
    }
}

