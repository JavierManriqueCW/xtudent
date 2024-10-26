package com.jmp.storage.database.di

import android.content.Context
import com.jmp.storage.database.XtudentDatabase
import com.jmp.storage.database.dao.ExamDao
import com.jmp.storage.database.dao.ExamQuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): XtudentDatabase = XtudentDatabase.getInstance(context)

    @Provides
    fun provideExamDao(
        database: XtudentDatabase
    ): ExamDao = database.examDao()

    @Provides
    fun provideQuestionDao(
        database: XtudentDatabase
    ): ExamQuestionDao = database.examQuestionDao()
}
