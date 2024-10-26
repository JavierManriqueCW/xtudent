package com.jmp.examsfeature.presentation.factory

import com.jmp.commons.utils.model.Exam

open class ViewModelFactory {

    fun makeExam(): Exam = Exam()

    fun makeExamList(): List<Exam> =
        listOf(
            makeExam(),
            makeExam(),
            makeExam()
        )

    companion object {
        const val EXAM_ID: Long = 0
    }
}
