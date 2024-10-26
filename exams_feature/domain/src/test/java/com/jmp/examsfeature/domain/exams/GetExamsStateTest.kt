package com.jmp.examsfeature.domain.exams

import com.jmp.examsfeature.domain.exams.interactor.GetExamsState
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [GetExamsState]
 */
@ExperimentalCoroutinesApi
class GetExamsStateTest {

    private val repository: ExamsRepository = mock()
    private val sut: GetExamsState = GetExamsState(repository)

    @Test
    fun `repository function getExamsCachedState is invoked`() = runTest {
        sut.invoke()
        verify(repository).getExamsCachedState()
    }
}
