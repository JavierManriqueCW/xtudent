package com.jmp.examsfeature.domain.exams

import com.jmp.commons.utils.model.Exam

object ExamsTestUtils {

    val Exam.Companion.EMPTY: Exam
        get() =
            Exam(
                id = 0,
                name = String(),
                description = String(),
                image = String(),
                examQuestions = emptyList()
            )
}
