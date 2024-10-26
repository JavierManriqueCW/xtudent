package com.jmp.examsfeature.domain.exams

import com.jmp.commons.utils.model.Exam
import com.jmp.examsfeature.domain.exams.ExamsTestUtils.EMPTY
import com.jmp.examsfeature.domain.exams.interactor.SaveExam
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [SaveExam]
 */
@ExperimentalCoroutinesApi
class SaveExamTest {

    private val repository: ExamsRepository = mock()
    private val sut: SaveExam = SaveExam(repository)

    @Test
    fun `repository function saveExam is invoked`() = runTest {
        sut.invoke(Exam.EMPTY)
        verify(repository).saveExam(any())
    }
}
