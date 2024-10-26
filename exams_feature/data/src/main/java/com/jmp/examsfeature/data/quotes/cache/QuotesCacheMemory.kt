package com.jmp.examsfeature.data.quotes.cache

import com.jmp.commons.utils.model.Quote
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import javax.inject.Inject

open class QuotesCacheMemory @Inject constructor() {

    private val _cache  = mutableListOf<Quote>()

    fun getQuotes(): Either<Failure, List<Quote>> =
        if (_cache.isEmpty())
                Either.Error(Failure.NoQuotes)
            else
                Either.Success(_cache)

    fun saveQuotes(list: List<Quote>) {
        _cache.clear()
        _cache.addAll(list)
    }
}
