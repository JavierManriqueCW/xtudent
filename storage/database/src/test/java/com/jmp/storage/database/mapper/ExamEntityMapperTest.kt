package com.jmp.storage.database.mapper

import com.jmp.storage.database.util.ExamsTestConstants.EXAM_QUESTION_ENTITY_LIST
import com.jmp.storage.database.util.ExamsTestConstants.EXAM_QUESTION_LIST
import com.jmp.storage.database.util.ExamsTestConstants.EXAM_WITH_QUESTIONS_ENTITY_LIST
import com.jmp.storage.database.util.ExamsTestConstants.PRIMARY_EXAM
import com.jmp.storage.database.util.ExamsTestConstants.PRIMARY_EXAM_ENTITY
import com.jmp.storage.database.util.ExamsTestConstants.PRIMARY_EXAM_ID
import com.jmp.storage.database.util.ExamsTestConstants.SINGLE_EXAM_LIST
import org.junit.Assert.assertEquals
import org.junit.Test

class ExamEntityMapperTest {

    private val sut: ExamEntityMapper =
        ExamEntityMapper()

    @Test
    fun `should map ExamWithQuestions to Exam`() {
        assertEquals(
            SINGLE_EXAM_LIST,
            sut.mapExamsFromEntity(EXAM_WITH_QUESTIONS_ENTITY_LIST)
        )
    }

    @Test
    fun `should map ExamQuestionEntity to ExamQuestion`() {
        assertEquals(
            EXAM_QUESTION_LIST,
            sut.mapQuestionsFromEntity(EXAM_QUESTION_ENTITY_LIST)
        )
    }

    @Test
    fun `should map Exam to ExamEntity`() {
        assertEquals(
            PRIMARY_EXAM_ENTITY,
            sut.mapExamToEntity(PRIMARY_EXAM)
        )
    }

    @Test
    fun `should map ExamQuestion to ExamQuestionEntity`() {
        assertEquals(
            EXAM_QUESTION_ENTITY_LIST,
            sut.mapQuestionsToEntity(EXAM_QUESTION_LIST, PRIMARY_EXAM_ID)
        )
    }
}
