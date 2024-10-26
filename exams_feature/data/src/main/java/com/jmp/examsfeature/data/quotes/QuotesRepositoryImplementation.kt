package com.jmp.examsfeature.data.quotes

import com.jmp.commons.utils.model.Quote
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.commons.utils.types.doOnSuccess
import com.jmp.examsfeature.data.quotes.cache.QuotesCacheMemory
import com.jmp.examsfeature.data.quotes.remote.NetworkHandler
import com.jmp.examsfeature.data.quotes.remote.QuotesRemoteMapper
import com.jmp.examsfeature.data.quotes.remote.QuotesService
import com.jmp.examsfeature.domain.quotes.repository.QuotesRepository
import javax.inject.Inject

open class QuotesRepositoryImplementation @Inject constructor(
    private val cacheMemory: QuotesCacheMemory,
    private val service: QuotesService,
    private var mapper: QuotesRemoteMapper,
    private val networkHandler: NetworkHandler
) : QuotesRepository {

    override suspend fun getQuotes(): Either<Failure, List<Quote>> =
        when (val cachedQuotes = cacheMemory.getQuotes()) {
            is Either.Success -> cachedQuotes
            is Either.Error -> {
                Either.request(networkHandler.isConnected()) {
                    mapper.mapFromRemote(service.getQuotes())
                }.doOnSuccess { remoteQuotes ->
                    cacheMemory.saveQuotes(remoteQuotes)
                }
            }
        }
}
