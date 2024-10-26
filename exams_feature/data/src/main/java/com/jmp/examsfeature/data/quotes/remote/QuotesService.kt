package com.jmp.examsfeature.data.quotes.remote

import com.jmp.examsfeature.data.quotes.remote.model.QuoteResponse
import retrofit2.http.GET

interface QuotesService {

    companion object {
        const val BASE_URL = "https://zenquotes.io/"
        private const val QUOTES_PATH = "api/quotes"
    }

    @GET(QUOTES_PATH)
    suspend fun getQuotes(): List<QuoteResponse>
}
