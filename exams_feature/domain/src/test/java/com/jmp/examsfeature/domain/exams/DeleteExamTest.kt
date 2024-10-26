package com.jmp.examsfeature.domain.exams

import com.jmp.commons.utils.model.Exam
import com.jmp.examsfeature.domain.exams.ExamsTestUtils.EMPTY
import com.jmp.examsfeature.domain.exams.interactor.DeleteExam
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [DeleteExam]
 */
@ExperimentalCoroutinesApi
class DeleteExamTest {

    private val repository: ExamsRepository = mock()
    private val sut: DeleteExam = DeleteExam(repository)

    @Test
    fun `repository function deleteExam is invoked`() = runTest {
        sut.invoke(Exam.EMPTY)
        verify(repository).deleteExam(any())
    }
}
