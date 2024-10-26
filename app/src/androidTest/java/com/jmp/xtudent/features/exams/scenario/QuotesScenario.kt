package com.jmp.xtudent.features.exams.scenario

import com.jmp.xtudent.core.MockWebServerManager
import com.jmp.xtudent.core.MockWebServerManager.Companion.ERROR_HTTP_CODE
import javax.inject.Inject

class QuotesScenario @Inject constructor(
    private val mockWebServerManager: MockWebServerManager
) {

    fun givenSomeQuotes() = apply {
        mockWebServerManager.enqueueResponse(responseFileName = GET_QUOTES_FULL_RESPONSE)
    }

    fun givenThatAnErrorOccurred() = apply {
        mockWebServerManager.enqueueResponse(responseCode = ERROR_HTTP_CODE)
    }

    companion object {
        const val GET_QUOTES_FULL_RESPONSE = "get_quotes_full_response.json"
    }
}
