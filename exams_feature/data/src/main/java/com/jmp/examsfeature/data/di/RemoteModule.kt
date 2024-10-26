package com.jmp.examsfeature.data.di

import androidx.multidex.BuildConfig
import com.google.gson.Gson
import com.jmp.examsfeature.data.quotes.remote.QuotesService
import com.jmp.examsfeature.data.quotes.remote.QuotesServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideQuotesService(
        gson: Gson
    ): QuotesService =
        QuotesServiceFactory.makeQuotesServiceFactory(
            BuildConfig.DEBUG,
            gson,
            QuotesService.BASE_URL.toHttpUrl()
        )
}
