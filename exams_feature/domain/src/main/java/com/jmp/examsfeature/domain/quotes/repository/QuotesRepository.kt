package com.jmp.examsfeature.domain.quotes.repository

import com.jmp.commons.utils.model.Quote
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface QuotesRepository {

    suspend fun getQuotes(): Either<Failure, List<Quote>>
}
