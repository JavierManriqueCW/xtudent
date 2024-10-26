package com.jmp.xtudent.core

import android.content.Context
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MockWebServerManager @Inject constructor(
    private val mockWebServer: MockWebServer,
    private val context: Context,
) {

    init { mockWebServer.start() }

    fun enqueueResponse(
        responseFileName: String? = null,
        responseCode: Int = SUCCESS_HTTP_CODE
    ) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getResponseFromAssets(responseFileName.orEmpty()))
        )
    }

    private fun getResponseFromAssets(responseFileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val assetManager = context.assets
            val inputStream = assetManager.open(responseFileName)
            val reader = BufferedReader(InputStreamReader(inputStream))

            reader.forEachLine { line ->
                stringBuilder.append(line).append("\n")
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    companion object {
        const val SUCCESS_HTTP_CODE = 200
        const val ERROR_HTTP_CODE = 404
    }
}
