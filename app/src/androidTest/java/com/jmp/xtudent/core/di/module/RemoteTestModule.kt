package com.jmp.xtudent.core.di.module

import android.content.Context
import com.google.gson.Gson
import com.jmp.examsfeature.data.di.RemoteModule
import com.jmp.examsfeature.data.quotes.remote.QuotesService
import com.jmp.examsfeature.data.quotes.remote.QuotesServiceFactory
import com.jmp.xtudent.core.MockWebServerManager
import com.valentinilk.shimmer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RemoteModule::class]
)
object RemoteTestModule {

    @Provides
    @Singleton
    fun provideMockWebServer() : MockWebServer = MockWebServer()

    @Provides
    @Singleton
    fun provideMockWebServerManager(
        mockWebServer: MockWebServer,
        @ApplicationContext context: Context,
    ): MockWebServerManager =
        MockWebServerManager(
            mockWebServer = mockWebServer,
            context = context
        )

    @Provides
    @Singleton
    fun provideQuotesService(
        mockWebServer: MockWebServer
    ): QuotesService =
        QuotesServiceFactory.makeQuotesServiceFactory(
            BuildConfig.DEBUG,
            Gson(),
            mockWebServer.url("/")
        )
}