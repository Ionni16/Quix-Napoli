package it.ionut.quiznapoli.data

import android.content.Context

class ScoreManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("quiz_napoli_prefs", Context.MODE_PRIVATE)

    fun getHighScore(): Int {
        return sharedPreferences.getInt("high_score", 0)
    }

    fun saveScore(score: Int) {
        val currentHigh = getHighScore()
        if (score > currentHigh) {
            sharedPreferences.edit().putInt("high_score", score).apply()
        }
    }

    fun isNewRecord(score: Int): Boolean {
        return score > getHighScore()
    }
}