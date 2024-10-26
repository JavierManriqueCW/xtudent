package com.jmp.commons.utils.model

data class ExamQuestion(
    val id: Long = 0,
    val question: String,
    val rightAnswer: String,
    val wrongAnswers: List<String>
)
