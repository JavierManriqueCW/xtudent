package com.jmp.commons.utils.model

data class Exam(
    val id: Long = 0,
    val name: String = String(),
    val description: String = String(),
    val image: String = String(),
    var examQuestions: List<ExamQuestion> = listOf()
) {
    companion object
}
