package com.jmp.examsfeature.domain.quotes

import com.jmp.examsfeature.domain.quotes.interactor.GetQuotes
import com.jmp.examsfeature.domain.quotes.repository.QuotesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [GetQuotes]
 */
@ExperimentalCoroutinesApi
class GetQuotesTest {

    private val repository: QuotesRepository = mock()
    private val sut: GetQuotes = GetQuotes(repository)

    @Test
    fun `repository function getQuotes is invoked`() = runTest {
        sut.invoke()
        verify(repository).getQuotes()
    }
}
