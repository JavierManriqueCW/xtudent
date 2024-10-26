package com.jmp.examsfeature.data.quotes.remote

import com.jmp.commons.utils.model.Quote
import com.jmp.examsfeature.data.quotes.remote.model.QuoteResponse
import javax.inject.Inject

open class QuotesRemoteMapper @Inject constructor() :
    RemoteMapper<List<Quote>, List<QuoteResponse>> {

    override fun mapFromRemote(data: List<QuoteResponse>): List<Quote> =
        data.map { quoteResponse ->
            Quote(
                text = quoteResponse.text,
                author = quoteResponse.author
                    .replace(", type.fit", String())
                    .replace("type.fit", String())
            )
        }
}
