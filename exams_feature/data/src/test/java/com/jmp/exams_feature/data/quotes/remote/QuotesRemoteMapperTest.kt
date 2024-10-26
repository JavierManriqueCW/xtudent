package com.jmp.exams_feature.data.quotes.remote

import com.jmp.commons.utils.model.Quote
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.EMPTY_AUTHOR_QUOTE
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.EMPTY_AUTHOR_QUOTES_RESPONSE_LIST
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTE
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTES_RESPONSE_LIST
import com.jmp.examsfeature.data.quotes.remote.QuotesRemoteMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class QuotesRemoteMapperTest {

    private val sut = QuotesRemoteMapper()

    @Test
    fun `should map QuoteResponse to Quote correctly`() {
        assertEquals(
            PRIMARY_QUOTE,
            sut.mapFromRemote(PRIMARY_QUOTES_RESPONSE_LIST).first()
        )
    }

    @Test
    fun `should handle empty author correctly`() {
        assertEquals(
            EMPTY_AUTHOR_QUOTE,
            sut.mapFromRemote(EMPTY_AUTHOR_QUOTES_RESPONSE_LIST).first()
        )
    }

    @Test
    fun `should handle empty list of QuoteResponse correctly`() {
        assertEquals(emptyList<Quote>(), sut.mapFromRemote(emptyList()))
    }
}
