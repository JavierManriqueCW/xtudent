package com.jmp.exams_feature.data.quotes.utils

import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTES_LIST
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.SECONDARY_QUOTES_LIST
import com.jmp.examsfeature.data.quotes.cache.QuotesCacheMemory

class QuotesScenario(
    private val cacheMemory: QuotesCacheMemory
) {

    fun givenCachedQuotes() {
        cacheMemory.saveQuotes(PRIMARY_QUOTES_LIST)
    }

    fun givenSeveralQuotesListsSaved() {
        cacheMemory.apply {
            saveQuotes(PRIMARY_QUOTES_LIST)
            saveQuotes(SECONDARY_QUOTES_LIST)
        }
    }
}
