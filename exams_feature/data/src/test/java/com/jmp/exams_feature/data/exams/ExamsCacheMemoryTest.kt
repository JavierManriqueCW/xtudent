package com.jmp.exams_feature.data.exams

import app.cash.turbine.test
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.MULTIPLE_EXAM_LIST
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM_ID
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.SECONDARY_EXAM_ID
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.SINGLE_EXAM_LIST
import com.jmp.examsfeature.data.exams.ExamsCacheMemory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExamsCacheMemoryTest {

    private val sut: ExamsCacheMemory = ExamsCacheMemory()

    @Test
    fun `should return the current state`() = runBlocking {
        sut.run {
            SINGLE_EXAM_LIST.let { exams ->
                saveExams(exams)
                getExamsCachedState().test {
                    assertEquals(exams, awaitItem())
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }
    }

    @Test
    fun `should return the exam with the given id if it exists`() {
        sut.run {
            saveExams(MULTIPLE_EXAM_LIST)
            assertEquals(
                PRIMARY_EXAM,
                getExam(PRIMARY_EXAM_ID)
            )
        }
    }

    @Test
    fun `should return null if the exam with the given id does not exist`() {
        assertNull(
            sut.run {
                saveExams(SINGLE_EXAM_LIST)
                sut.getExam(SECONDARY_EXAM_ID)
            }
        )
    }

    @Test
    fun `should update the state with the given exams`() = runBlocking {
        SINGLE_EXAM_LIST.let { exams ->
            sut.run {
                saveExams(exams)
                getExamsCachedState().test {
                    assertEquals(exams, awaitItem())
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }
    }

    @Test
    fun `should add the given exam to the state`() = runBlocking {
        sut.run {
            saveExam(PRIMARY_EXAM)
            getExamsCachedState().test {
                assertEquals(SINGLE_EXAM_LIST, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `should remove the exam with the given id from the state`() = runBlocking {
        sut.run {
            saveExams(MULTIPLE_EXAM_LIST)
            deleteExam(SECONDARY_EXAM_ID)
            getExamsCachedState().test {
                assertEquals(SINGLE_EXAM_LIST, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
