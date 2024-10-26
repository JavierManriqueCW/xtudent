package com.jmp.exams_feature.data.quotes

import android.util.Log
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.exams_feature.data.quotes.remote.NetworkScenario
import com.jmp.exams_feature.data.quotes.utils.QuotesScenario
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTES_LIST
import com.jmp.exams_feature.data.quotes.utils.QuotesTestConstants.PRIMARY_QUOTES_RESPONSE_LIST
import com.jmp.examsfeature.data.quotes.QuotesRepositoryImplementation
import com.jmp.examsfeature.data.quotes.cache.QuotesCacheMemory
import com.jmp.examsfeature.data.quotes.remote.NetworkHandler
import com.jmp.examsfeature.data.quotes.remote.QuotesRemoteMapper
import com.jmp.examsfeature.data.quotes.remote.QuotesService
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.Mockito.`when` as whenever

class QuotesRepositoryImplementationTest {

    private val cacheMemory: QuotesCacheMemory = QuotesCacheMemory()
    private val service: QuotesService = mock()
    private val mapper: QuotesRemoteMapper = mock()
    private val networkHandler: NetworkHandler = NetworkHandler(mock())
    private val staticLogFunctions: MockedStatic<Log> = Mockito.mockStatic(Log::class.java)

    private val sut: QuotesRepositoryImplementation =
        QuotesRepositoryImplementation(
            cacheMemory,
            service,
            mapper,
            networkHandler
        )
    private val quotesScenario: QuotesScenario = QuotesScenario(cacheMemory)
    private val networkScenario: NetworkScenario =
        NetworkScenario(
            networkHandler,
            mock(),
            mock()
        )

    @After
    fun tearDown() {
        staticLogFunctions.close()
    }

    @Test
    fun `should return cached quotes when available`() = runTest {
        quotesScenario.givenCachedQuotes()

        assertEquals(
            Either.Success(PRIMARY_QUOTES_LIST),
            sut.getQuotes()
        )
    }

    @Test
    fun `should return remote quotes when cache is empty and network is available`() = runTest {
        networkScenario.givenCellularNetworkConnection()

        whenever(service.getQuotes()).thenReturn(PRIMARY_QUOTES_RESPONSE_LIST)
        whenever(mapper.mapFromRemote(any())).thenReturn(PRIMARY_QUOTES_LIST)

        assertEquals(
            Either.Success(PRIMARY_QUOTES_LIST),
            sut.getQuotes()
        )
    }

    @Test
    fun `should return error when cache is empty and network is unavailable`() = runTest {
        staticLogFunctions.`when`<Int> { Log.e(any(), any()) }.thenReturn(Log.ERROR)
        networkScenario.givenConnectionWithoutInternet()

        assertEquals(
            Failure.NoConnectivity().javaClass,
            sut.getQuotes().run {
                (this as Either.Error).failure.javaClass
            }
        )
    }
}
