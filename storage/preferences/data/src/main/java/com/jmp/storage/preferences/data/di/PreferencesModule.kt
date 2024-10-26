package com.jmp.storage.preferences.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jmp.storage.preferences.data.SharedPreferencesManager
import com.jmp.storage.preferences.data.SharedPreferencesRepositoryImplementation
import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    @Singleton
    fun providesSharedPreferencesManager(
        sharedPreferences: SharedPreferences
    ): SharedPreferencesManager =
        SharedPreferencesManager(
            sharedPreferences
        )

    @Provides
    @Singleton
    fun providePreferencesRepository(
        manager: SharedPreferencesManager
    ): PreferencesRepository =
        SharedPreferencesRepositoryImplementation(
            manager
        )

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
}
