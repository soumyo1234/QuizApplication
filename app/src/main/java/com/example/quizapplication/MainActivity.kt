package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSoftware = findViewById<Button>(R.id.btnSoftware)
        val btnDS = findViewById<Button>(R.id.btnDS)
        val btnScoreboard = findViewById<Button>(R.id.btnScoreboard)

        btnSoftware.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("CATEGORY", "SOFTWARE")
            startActivity(intent)
        }

        btnDS.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("CATEGORY", "DS")
            startActivity(intent)
        }

        btnScoreboard.setOnClickListener {
            val intent = Intent(this, ScoreboardActivity::class.java)
            startActivity(intent)
        }
    }
}