package com.jmp.examsfeature.data.exams

import com.jmp.commons.utils.model.Exam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class ExamsCacheMemory @Inject constructor() {

    private val state = MutableStateFlow(emptyList<Exam>())

    fun getExamsCachedState(): StateFlow<List<Exam>> = state

    fun getExam(examId: Long): Exam? =
        state.value.find { exam -> exam.id == examId }

    fun saveExams(exams: List<Exam>) {
        state.value = exams
    }

    fun saveExam(exam: Exam) {
        state.value += exam
    }

    fun deleteExam(id: Long) {
        state.value.find { e -> e.id == id }?.let { cachedExam ->
            state.value -= cachedExam
        }
    }
}
