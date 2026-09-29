package com.example.quizapplication

data class Question(
    val category: String,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswer: String,
    val feedback: String
)