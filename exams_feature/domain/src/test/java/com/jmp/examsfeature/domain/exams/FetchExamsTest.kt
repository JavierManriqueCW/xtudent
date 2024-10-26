package com.jmp.examsfeature.domain.exams

import com.jmp.examsfeature.domain.exams.interactor.FetchExams
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [FetchExams]
 */
@ExperimentalCoroutinesApi
class FetchExamsTest {

    private val repository: ExamsRepository = mock()
    private val sut: FetchExams = FetchExams(repository)

    @Test
    fun `repository function fetchExams is invoked`() = runTest {
        sut.invoke()
        verify(repository).fetchExams()
    }
}
