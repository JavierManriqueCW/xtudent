package com.jmp.examsfeature.domain.quotes.interactor

import com.jmp.commons.utils.model.Quote
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.quotes.repository.QuotesRepository
import javax.inject.Inject

open class GetQuotes @Inject constructor(
    private val repository: QuotesRepository
) {

    open suspend operator fun invoke(): Either<Failure, List<Quote>> {
        return repository.getQuotes()
    }
}
