package com.example.quizapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize SharedPreferences
        prefs = getSharedPreferences("Scores", MODE_PRIVATE)

        // Get score from intent
        val score = intent.getIntExtra("SCORE", 0)
        val category = intent.getStringExtra("CATEGORY") ?: "Unknown"

        // Initialize views
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMedal = findViewById<TextView>(R.id.tvMedal)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Display score
        tvScore.text = "Your Score: $score / 10"

        // Determine and display medal
        val medal = getMedal(score)
        tvMedal.text = medal

        // Save score to SharedPreferences
        saveScore(score, category)

        // Home button - go back to main screen
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    /**
     * Get medal based on score
     * Gold: >= 9
     * Silver: >= 8
     * Bronze: >= 4
     * Try Again: < 4
     */
    private fun getMedal(score: Int): String {
        return when {
            score >= 9 -> "🏆 Gold Medal!\nExcellent Performance!"
            score >= 8 -> "🥈 Silver Medal!\nGreat Job!"
            score >= 4 -> "🥉 Bronze Medal!\nGood Effort!"
            else -> "Try Again!\nKeep Practicing!"
        }
    }

    /**
     * Save score to SharedPreferences with timestamp
     */
    private fun saveScore(score: Int, category: String) {
        val editor = prefs.edit()
        val timestamp = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val dateString = dateFormat.format(Date(timestamp))

        // Save with format: "Category - Score - Date"
        val scoreEntry = "$category - $score/10 - $dateString"
        editor.putString("score_$timestamp", scoreEntry)
        editor.apply()
    }
}