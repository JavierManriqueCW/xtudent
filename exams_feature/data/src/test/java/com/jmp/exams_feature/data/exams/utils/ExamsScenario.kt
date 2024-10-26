package com.jmp.exams_feature.data.exams.utils

import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.EXAM_QUESTION_ENTITY_LIST
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM_ENTITY
import com.jmp.examsfeature.data.exams.ExamsCacheMemory
import com.jmp.storage.database.XtudentDatabase
import com.jmp.storage.database.mapper.ExamEntityMapper
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class ExamsScenario(
    private val cacheMemory: ExamsCacheMemory,
    private val database: XtudentDatabase,
    private val mapper: ExamEntityMapper
) {

    fun stubMapExamToEntity(): ExamsScenario = apply {
        whenever(mapper.mapExamToEntity(any())).thenReturn(PRIMARY_EXAM_ENTITY)
    }

    fun stubMapExamsFromEntity(): ExamsScenario = apply {
        whenever(mapper.mapExamsFromEntity(any()))
            .thenReturn(listOf(PRIMARY_EXAM))
    }

    suspend fun givenAnExamStoredInDb() {
        database.examDao().save(PRIMARY_EXAM_ENTITY)
        database.examQuestionDao().save(EXAM_QUESTION_ENTITY_LIST)
    }

    fun givenAnExamStoredCache(): ExamsScenario = apply {
        cacheMemory.saveExam(PRIMARY_EXAM)
    }
}
