package com.example.quizapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    
    private lateinit var questionList: ArrayList<Question>
    private var currentQuestion: Question? = null
    private var score = 0
    private var questionCount = 0
    private var category = ""
    
    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbOptionA: RadioButton
    private lateinit var rbOptionB: RadioButton
    private lateinit var rbOptionC: RadioButton
    private lateinit var rbOptionD: RadioButton
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbOptionA = findViewById(R.id.rbOptionA)
        rbOptionB = findViewById(R.id.rbOptionB)
        rbOptionC = findViewById(R.id.rbOptionC)
        rbOptionD = findViewById(R.id.rbOptionD)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Set tags for answer identification
        rbOptionA.tag = "A"
        rbOptionB.tag = "B"
        rbOptionC.tag = "C"
        rbOptionD.tag = "D"

        // Get category from intent
        category = intent.getStringExtra("CATEGORY") ?: "Unknown"

        // Select question list based on category
        @Suppress("UNCHECKED_CAST")
        questionList = if (category == "SOFTWARE") {
            QuestionData.softwareQuestions.clone() as ArrayList<Question>
        } else {
            QuestionData.dataStructureQuestions.clone() as ArrayList<Question>
        }

        // Load first question
        loadNextQuestion()

        // Submit button listener
        btnSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    /**
     * Load next question from the ArrayList
     */
    private fun loadNextQuestion() {
        if (questionList.isEmpty()) {
            // All questions answered - go to result
            goToResult()
            return
        }

        currentQuestion = getRandomQuestion(questionList)
        if (currentQuestion != null) {
            displayQuestion()
            questionCount++
        }
    }

    /**
     * Get random question from list and remove it
     */
    private fun getRandomQuestion(list: ArrayList<Question>): Question? {
        if (list.isEmpty()) return null

        val index = (Math.random() * list.size).toInt()
        val question = list[index]
        list.removeAt(index)

        return question
    }

    /**
     * Display question and options on screen
     */
    private fun displayQuestion() {
        radioGroup.clearCheck()

        tvQuestionNumber.text = "Question $questionCount of 10"
        tvQuestion.text = currentQuestion?.question

        rbOptionA.text = currentQuestion?.optionA
        rbOptionB.text = currentQuestion?.optionB
        rbOptionC.text = currentQuestion?.optionC
        rbOptionD.text = currentQuestion?.optionD
    }

    /**
     * Check if selected answer is correct
     */
    private fun checkAnswer() {
        val selectedId = radioGroup.checkedRadioButtonId

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        val selectedAnswer = selectedRadioButton.tag.toString()

        if (currentQuestion != null) {
            if (selectedAnswer == currentQuestion!!.correctAnswer) {
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Wrong! Correct answer: ${currentQuestion!!.correctAnswer}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Move to next question or go to result
        if (questionCount < 10) {
            loadNextQuestion()
        } else {
            goToResult()
        }
    }

    /**
     * Navigate to result screen
     */
    private fun goToResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
        finish()
    }
}