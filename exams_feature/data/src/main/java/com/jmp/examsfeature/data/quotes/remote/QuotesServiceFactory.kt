package com.jmp.examsfeature.data.quotes.remote

import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object QuotesServiceFactory {

    private const val DEFAULT_TIMEOUT_IN_SECONDS: Long = 60

    fun makeQuotesServiceFactory(
        isDebug: Boolean,
        gson: Gson,
        url: HttpUrl
    ): QuotesService =
        makeQuotesService(
            makeOkHttpClient(makeLoggingInterceptor(isDebug)),
            gson,
            url
        )

    private fun makeQuotesService(
        okHttpClient: OkHttpClient,
        gson: Gson,
        url: HttpUrl
    ): QuotesService {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(QuotesService::class.java)
    }

    private fun makeOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
}
