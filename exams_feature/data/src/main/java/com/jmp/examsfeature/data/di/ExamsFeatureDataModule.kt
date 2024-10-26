package com.jmp.examsfeature.data.di

import android.content.ContentResolver
import android.content.Context
import com.google.gson.Gson
import com.jmp.examsfeature.data.quotes.QuotesRepositoryImplementation
import com.jmp.examsfeature.data.quotes.cache.QuotesCacheMemory
import com.jmp.examsfeature.data.quotes.remote.NetworkHandler
import com.jmp.examsfeature.data.quotes.remote.QuotesRemoteMapper
import com.jmp.examsfeature.data.quotes.remote.QuotesService
import com.jmp.examsfeature.data.quotes.remote.gson.GsonProvider
import com.jmp.examsfeature.data.xlsx.XlsxRepositoryImplementation
import com.jmp.examsfeature.data.xlsx.XlsxWorkbookMapper
import com.jmp.examsfeature.domain.quotes.repository.QuotesRepository
import com.jmp.examsfeature.domain.xlsx.repository.XlsxRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExamsFeatureDataModule {

    @Singleton
    @Provides
    fun provideNetworkHandler(
        @ApplicationContext context: Context
    ): NetworkHandler = NetworkHandler(context)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonProvider.makeGson()

    @Provides
    @Singleton
    fun provideQuotesRepository(
        cacheMemory: QuotesCacheMemory,
        service: QuotesService,
        mapper: QuotesRemoteMapper,
        networkHandler: NetworkHandler
    ): QuotesRepository =
        QuotesRepositoryImplementation(
            cacheMemory,
            service,
            mapper,
            networkHandler
        )

    @Provides
    @Singleton
    fun providesXlsxRepository(
        contentResolver: ContentResolver,
        mapper: XlsxWorkbookMapper
    ): XlsxRepository =
        XlsxRepositoryImplementation(
            contentResolver,
            mapper
        )
}
