package com.jmp.exams_feature.data.quotes.utils

import com.jmp.commons.utils.model.Quote
import com.jmp.examsfeature.data.quotes.remote.model.QuoteResponse

object QuotesTestConstants {

    private const val PRIMARY_QUOTE_TEXT: String = "Lorem ipsum"
    private const val SECONDARY_QUOTE_TEXT = "Dolor sit amet"
    private const val QUOTE_AUTHOR_RESPONSE_TEXT: String =
        "Consectetur adipiscing elit, type.fit"
    private const val QUOTE_AUTHOR_TEXT: String = "Consectetur adipiscing elit"

    val PRIMARY_QUOTE: Quote =
        Quote(
            text = PRIMARY_QUOTE_TEXT,
            author = QUOTE_AUTHOR_TEXT
        )

    private val SECONDARY_QUOTE: Quote =
        Quote(
            text = SECONDARY_QUOTE_TEXT,
            author = QUOTE_AUTHOR_TEXT
        )

    val EMPTY_AUTHOR_QUOTE: Quote =
        Quote(
            text = PRIMARY_QUOTE_TEXT,
            author = String()
        )

    private val PRIMARY_QUOTE_RESPONSE: QuoteResponse =
        QuoteResponse(
            text = PRIMARY_QUOTE_TEXT,
            author = QUOTE_AUTHOR_RESPONSE_TEXT
        )

    private val EMPTY_AUTHOR_QUOTE_RESPONSE: QuoteResponse =
        QuoteResponse(
            text = PRIMARY_QUOTE_TEXT,
            author = String()
        )

    val PRIMARY_QUOTES_LIST = listOf(PRIMARY_QUOTE)
    val SECONDARY_QUOTES_LIST = listOf(SECONDARY_QUOTE)
    val PRIMARY_QUOTES_RESPONSE_LIST = listOf(PRIMARY_QUOTE_RESPONSE)
    val EMPTY_AUTHOR_QUOTES_RESPONSE_LIST = listOf(EMPTY_AUTHOR_QUOTE_RESPONSE)
}
