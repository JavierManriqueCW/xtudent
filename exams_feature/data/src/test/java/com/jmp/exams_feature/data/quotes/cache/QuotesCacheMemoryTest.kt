package com.jmp.exams_feature.data.quotes.cache

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.exams_feature.data.quotes.utils.QuotesScenario
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTES_LIST
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.SECONDARY_QUOTES_LIST
import com.jmp.examsfeature.data.quotes.cache.QuotesCacheMemory
import org.junit.Assert.assertEquals
import org.junit.Test

class QuotesCacheMemoryTest {

    private val quotesCacheMemory = QuotesCacheMemory()
    private val scenario = QuotesScenario(quotesCacheMemory)

    @Test
    fun `should return Error when cache is empty`() {
        assertEquals(
            Either.Error(Failure.NoQuotes),
            quotesCacheMemory.getQuotes()
        )
    }

    @Test
    fun `should return Success with cached quotes when cache is not empty`() {
        scenario.givenCachedQuotes()
        assertEquals(
            Either.Success(PRIMARY_QUOTES_LIST),
            quotesCacheMemory.getQuotes()
        )
    }

    @Test
    fun `should clear existing cache and add new quotes`() {
        scenario.givenSeveralQuotesListsSaved()
        assertEquals(
            Either.Success(SECONDARY_QUOTES_LIST),
            quotesCacheMemory.getQuotes()
        )
    }
}
