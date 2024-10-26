package com.jmp.examsfeature.presentation.detail.state

data class ExamQuestionState(
    val id: Long,
    val question: String,
    val correctAnswer: String,
    val firstWrongAlternative: String,
    val secondWrongAlternative: String,
    val thirdWrongAlternative: String
)
