package com.example.quizapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreboardActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scoreboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize SharedPreferences
        prefs = getSharedPreferences("Scores", MODE_PRIVATE)

        // Initialize views
        val tvScoreboard = findViewById<TextView>(R.id.tvScoreboard)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Load and display scores
        loadScores(tvScoreboard)

        // Home button
        btnHome.setOnClickListener {
            finish()
        }
    }

    /**
     * Load scores from SharedPreferences and display them
     */
    private fun loadScores(tvScoreboard: TextView) {
        val allScores = prefs.all

        if (allScores.isEmpty()) {
            tvScoreboard.text = "No scores yet. Take a quiz to get started!"
            return
        }

        val scoreList = allScores.values.toList().filterIsInstance<String>()
        val sortedScores = scoreList.sortedByDescending { it.substringAfterLast(" ").toIntOrNull() ?: 0 }

        val scoreboard = StringBuilder("🏆 TOP SCORES 🏆\n\n")
        sortedScores.forEachIndexed { index, score ->
            scoreboard.append("${index + 1}. $score\n")
        }

        tvScoreboard.text = scoreboard.toString()
    }

    /**
     * Save score to SharedPreferences
     * Call this from ResultActivity after showing medal
     */
    fun saveScore(name: String, score: Int) {
        val editor = prefs.edit()
        val timestamp = System.currentTimeMillis()
        editor.putString("score_$timestamp", "$name - $score")
        editor.apply()
    }
}