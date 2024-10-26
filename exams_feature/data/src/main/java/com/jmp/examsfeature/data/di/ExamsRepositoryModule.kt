package com.jmp.examsfeature.data.di

import com.jmp.examsfeature.data.exams.ExamsCacheMemory
import com.jmp.examsfeature.data.exams.ExamsRepositoryImplementation
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.storage.database.XtudentDatabase
import com.jmp.storage.database.mapper.ExamEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExamsRepositoryModule {

    @Provides
    @Singleton
    fun provideExamsRepository(
        database: XtudentDatabase,
        cacheMemory: ExamsCacheMemory,
        mapper: ExamEntityMapper
    ): ExamsRepository =
        ExamsRepositoryImplementation(
            database = database,
            cacheMemory = cacheMemory,
            mapper = mapper
        )
}
